package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.comparators.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.Comparator;
import com.zemoso.zinteract.decisiontable.*;
import groovy.util.Eval;

import java.util.*;

public class DtExecutor extends AbstractDtExecutor{

	private DecisionTable dT = null;
	private String[] options;
	private String dT_json;

	public DtExecutor(String[] options,String dt) {
		this.options = options;
		this.dT_json = dt;
	}

	public DecisionTable getDecisionTable(){
		if(dT != null){
			return dT;
		}
		else {
			dT = createDT();
		}
		return dT;
	}


//	public DtRow getFirstMatch(HashMap<String,String> value) {
//		ArrayList<DtRow> allMatches = findMatches(value,true);
//		if(allMatches.size() > 0){
//			return allMatches.get(0);
//		}
//		else return null;
//
//	}

	public Map getFirstMatchActionResults(Map<String,String> value){
		DtResult result=getFirstMatch(value);
		return result.getActionResults();
	}

	public List<Map<String, Map<String, String>>> getAllActionResults(Map<String,String> value){
		List actionResults=new ArrayList();
		List<DtResult> results=getAllMatches(value);
		for(DtResult result: results){
			actionResults.add(result.getActionResults());
		}
		return actionResults;
	}

	public DtResult getFirstMatch(Map<String,String> value) {
		List<DtResult> allMatches = findMatches(value,true);
		if(allMatches.size() > 0){
			return allMatches.get(0);
		}
		else return null;

	}

	public List<DtResult> getAllMatches(Map<String,String> value) {
		return findMatches(value,false);
	}

	private List<DtResult> findMatches(Map<String, String> valueMap, Boolean firstOnly){
		AbstractComparatorFactory cFactory = AbstractComparatorFactory.getComparatorFactory();
		Iterator i,j;
		DtCondition dtCondition;
		Comparator comparator;
		Boolean match = false;

		List<DtResult> results = new ArrayList<DtResult>();
		Map<String,ConditionValue> conditionValues = getConditionValues(valueMap);
		Map<String,Enum> headerConditions = getDecisionTable().getHeader().getConditions();
		Boolean ignoreCase = getDecisionTable().getIgnoreCase();
		Map<String,Enum> dtConditions = getDecisionTable().getHeader().getConditions();
		for(DtRow row : getDecisionTable().getDt()) {
			i = dtConditions.entrySet().iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				dtCondition = row.getConditionValues().get(me.getKey().toString());
				if(dtCondition ==null){
					continue;
				}
				comparator = cFactory.getComparator(dtCondition.getComparatorName());
                ConditionValue cV;
				String value=valueMap.get(me.getKey().toString());
				Enum dataType = headerConditions.get(me.getKey().toString());
				cV = getConditionValue(value,dataType);
				match = comparator.satisfies(dtCondition,cV,ignoreCase);
				if(!match) {
					break;
				}
			}
			if(match && row.getScripts()!=null) {
				j = row.getScripts().entrySet().iterator();
				while (j.hasNext()) {
					Map.Entry me = (Map.Entry) j.next();
					DtScript script = (DtScript) me.getValue();
					if(!script.solve(valueMap)){
						match=false;
						break;
					}
				}
			}
			if(match) {
				DtResult result=new DtResult();
				result.setVariables(valueMap);
				result.setRow(row);
				results.add(result);
			}
			if(match && firstOnly){
				break;
			}
		}
		return results;
	}

    private ConditionValue solveScript(String key, Map<String, String> variables) {
		String keyCopy=key;
		for ( String var : variables.keySet() ) {
			if(keyCopy.contains(var)){
				keyCopy = keyCopy.replaceAll(var, variables.get(var));
			}
		}
		String val="";
		try {
			 val=  Eval.me(keyCopy).toString();
		} catch (groovy.lang.MissingPropertyException e){
			return null;
		}
		Map<String,Enum> headerConditions = getDecisionTable().getHeader().getConditions();
		Enum dataType = headerConditions.get(key);
		return getConditionValue(val,dataType);
    }

    private DecisionTable createDT() {
		DtCreater dtCreater= new DtCreater(dT_json);
		return dtCreater.createDtModel();
	}

	private Map<String,ConditionValue> getConditionValues(Map<String,String> value){
		Map<String,ConditionValue> conditionValues = new HashMap<String, ConditionValue>();
		DtHeader header = getDecisionTable().getHeader();
		Map<String,Enum> headerConditions = header.getConditions();
		Iterator i;
		ConditionValue cValue;
		i = value.entrySet().iterator();
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			Enum dataType = headerConditions.get(me.getKey());
			if(dataType == null){
				continue;
			}
			cValue=getConditionValue(me.getValue().toString(),dataType);
			conditionValues.put(me.getKey().toString(),cValue);
		}
		return conditionValues;
	}

	private ConditionValue getConditionValue(String value,Enum dataType){
		ConditionValue cValue;
		if(dataType == null){
			return null;
		}
		if(dataType.equals(StringConstants.DATATYPE_STRING)){
			cValue = new ConditionValue(value);
		}
		else if(dataType.equals(StringConstants.DATATYPE_LONG)){
			cValue = new ConditionValue(Long.valueOf(value).longValue());
		}
		else if(dataType.equals(StringConstants.DATATYPE_DOUBLE)){
			cValue = new ConditionValue(Double.valueOf(value).doubleValue());
		}
		else if(dataType.equals(StringConstants.DATATYPE_BOOLEAN)){
			cValue = new ConditionValue(Boolean.valueOf(value).booleanValue());
		}
		else {
			cValue = new ConditionValue(value);
		}
		return cValue;
	}
}


 