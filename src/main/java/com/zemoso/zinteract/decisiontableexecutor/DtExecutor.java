package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.comparators.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.Comparator;
import com.zemoso.zinteract.decisiontable.*;

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


	public DtRow getFirstMatch(HashMap<String,String> value) {
		ArrayList<DtRow> allMatches = findMatches(value,true);
		if(allMatches.size() > 0){
			return allMatches.get(0);
		}
		else return null;

	}

	public ArrayList<DtRow> getAllMatches(HashMap<String,String> value) {
		ArrayList<DtRow> allMatches = findMatches(value,false);
		return allMatches;
	}

	private ArrayList<DtRow> findMatches(HashMap<String,String> value, Boolean firstOnly){
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
				dtCondition = row.getConditionValues().get(me.getKey());
				comparator = cFactory.getComparator(dtCondition.getComparatorName());
				ConditionValue cV = (ConditionValue) conditionValues.get(me.getKey());
				match = comparator.satisfies(dtCondition,cV,ignoreCase);
				if(!match) {
					break;
				}
			}

			if(match) {
				rows.add(row);
			}
			if(match && firstOnly){
				break;
			}
		}
		return rows;
	}

	private DecisionTable createDT() {
		DtCreater dtCreater= new DtCreater(dT_json);
		DecisionTable dt = dtCreater.createDtModel();
		return dt;
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
			if(dataType.equals(StringConstants.DATATYPE_STRING)){
				cValue = new ConditionValue(me.getValue().toString());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(dataType.equals(StringConstants.DATATYPE_LONG)){
				cValue = new ConditionValue(Long.valueOf(me.getValue().toString()).longValue());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(dataType.equals(StringConstants.DATATYPE_DOUBLE)){
				cValue = new ConditionValue(Double.valueOf(me.getValue().toString()).doubleValue());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(dataType.equals(StringConstants.DATATYPE_BOOLEAN)){
				cValue = new ConditionValue(Boolean.valueOf(me.getValue().toString()).booleanValue());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else {
				cValue = new ConditionValue(me.getValue().toString());
				conditionValues.put(me.getKey().toString(),cValue);
			}
		}
		return conditionValues;
	}
}