package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.comparators.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.Comparator;
import com.zemoso.zinteract.decisiontable.*;
import groovy.util.Eval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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


	public DtRow getFirstMatch(HashMap<String,String> value) {
		ArrayList<DtRow> allMatches = findMatches(value,true);
		if(allMatches.size() > 0){
			return allMatches.get(0);
		}
		else return null;

	}

	public ArrayList<DtRow> getAllMatches(HashMap<String,String> value) {
		return findMatches(value,false);
	}

	private ArrayList<DtRow> findMatches(HashMap<String, String> value, Boolean firstOnly){
		AbstractComparatorFactory cFactory = AbstractComparatorFactory.getComparatorFactory();
		Iterator i;
		DtCondition dtCondition;
		Comparator comparator;
		Boolean match = false;
		ArrayList<DtRow> rows = new ArrayList<DtRow>();
		HashMap<String,ConditionValue> conditionValues = getConditionValues(value);
		Boolean ignoreCase = getDecisionTable().getIgnoreCase();
		HashMap<String,Enum> dtConditions = getDecisionTable().getHeader().getConditions();
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
                if(dtCondition.isScripted()){
                    cV=solveScript( me.getKey().toString(),value);
                } else {
                    cV = conditionValues.get(me.getKey().toString());
                }
				if(cV ==null){
					match=false;
				} else {
					match = comparator.satisfies(dtCondition,cV,ignoreCase);
				}
				if(!match) {
					break;
				}
			}

			if(match) {
				Iterator it = row.getActions().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					DtAction action = (DtAction) pair.getValue();
					if(action.isScripted()){
						action.setVariables(value);
					}
				}
				rows.add(row);
			}
			if(match && firstOnly){
				break;
			}
		}
		return rows;
	}

    private ConditionValue solveScript(String key, HashMap<String, String> variables) {
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
		HashMap<String,Enum> headerConditions = getDecisionTable().getHeader().getConditions();
		Enum dataType = headerConditions.get(key);
		return getConditionValue(val,dataType);
    }

    private DecisionTable createDT() {
		DtCreater dtCreater= new DtCreater(dT_json);
		return dtCreater.createDtModel();
	}

	private HashMap<String,ConditionValue> getConditionValues(HashMap<String,String> value){
		HashMap<String,ConditionValue> conditionValues = new HashMap<String, ConditionValue>();
		DtHeader header = getDecisionTable().getHeader();
		HashMap<String,Enum> headerConditions = header.getConditions();
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


 