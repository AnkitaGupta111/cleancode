package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.comparators.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.Comparator;
import com.zemoso.zinteract.comparators.ComparatorFactory;
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

	public DecisionTable getDt(){
		if(dT != null){
			return dT;
		}
		else {
			dT = createDT();
		}
		return dT;
	}


	public DtRow getFirstMatch(HashMap<String,String> value, StringConstants caseSensivity) {
		ArrayList<DtRow> allMatches = findMatches(value,caseSensivity,true);
		if(allMatches.size() > 0){
			return allMatches.get(0);
		}
		else return null;

	}

	public ArrayList<DtRow> getAllMatches(HashMap<String,String> value, StringConstants caseSensivity) {
		ArrayList<DtRow> allMatches = findMatches(value,caseSensivity,false);
		return allMatches;
	}

	private ArrayList<DtRow> findMatches(HashMap<String,String> value, StringConstants caseSensivity, Boolean firstOnly){
		AbstractComparatorFactory cFactory = AbstractComparatorFactory.getComparatorFactory();
		Iterator i;
		DtCondition dtCondition;
		Comparator comparator;
		Boolean match = false;
		ArrayList<DtRow> rows = new ArrayList<DtRow>();
		HashMap<String,ConditionValue> conditionValues = getConditionValues(value);
		for(DtRow row : dT.getDt()) {
			i = conditionValues.entrySet().iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				dtCondition = row.getConditionValues().get(me.getKey());
				comparator = cFactory.getComparator(dtCondition.getComparatorName());
				ConditionValue cV = (ConditionValue) me.getValue();
				match = comparator.satisfies(dtCondition,cV,caseSensivity);
				if(!match) {
					break;
				}
			}

			if(match) {
				rows.add(row);
			}
			if(firstOnly){
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
		DtHeader header = getDt().getHeader();
		HashMap<String,Enum> headerConditions = header.getConditions();
		Iterator i;
		ConditionValue cValue;
		i = value.entrySet().iterator();
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			if(headerConditions.get(me.getKey()).equals(StringConstants.DATATYPE_STRING)){
				cValue = new ConditionValue(me.getValue().toString());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(headerConditions.get(me.getKey()).equals(StringConstants.DATATYPE_LONG)){
				cValue = new ConditionValue(Long.valueOf(me.getValue().toString()).longValue());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(headerConditions.get(me.getKey()).equals(StringConstants.DATATYPE_DOUBLE)){
				cValue = new ConditionValue(Double.valueOf(me.getValue().toString()).doubleValue());
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