package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.comparators.Comparator;
import com.zemoso.zinteract.comparators.ComparatorFactory;
import com.zemoso.zinteract.decisiontable.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DtExecutor{

	private DecisionTable dT;

	public DtExecutor(String[] options,String dt) {
		DtCreater creater = new DtCreater(dt);
		dT = creater.createDtModel(dt);
	}

	public DecisionTable getDt(){
		return dT;
	}

	public DtRow execute(HashMap<String,String> value) {
		ComparatorFactory cFactory = (ComparatorFactory)ComparatorFactory.getComparatorFactory();
		Iterator i;
		DtCondition dtCondition;
		Comparator comparator;
		Boolean match = false;
		HashMap<String,ConditionValue> conditionValues = getConditionValues(value);
		for(DtRow row : dT.getDt()) {
			i = conditionValues.entrySet().iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				dtCondition = row.getConditionValues().get(me.getKey());
				comparator = cFactory.getComparator(dtCondition.getComparatorName());
				ConditionValue cV = (ConditionValue) me.getValue();
				match = comparator.satisfies(dtCondition,cV);
				if(!match) {
					break;
				}
			}

			if(match) {
				return row;
			}
		}
		return null;
	}

	private HashMap<String,ConditionValue> getConditionValues(HashMap<String,String> value){
		HashMap<String,ConditionValue> conditionValues = new HashMap<String, ConditionValue>();
		DtHeader header = this.dT.getHeader();
		Iterator i;
		ConditionValue cValue;
		i = value.entrySet().iterator();
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			if(header.conditions.get(me.getKey()).equals(StringConstants.DATATYPE_STRING)){
				cValue = new ConditionValue(me.getValue().toString());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(header.conditions.get(me.getKey()).equals(StringConstants.DATATYPE_LONG)){
				cValue = new ConditionValue(Long.valueOf(me.getValue().toString()).longValue());
				conditionValues.put(me.getKey().toString(),cValue);
			}
			else if(header.conditions.get(me.getKey()).equals(StringConstants.DATATYPE_DOUBLE)){
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