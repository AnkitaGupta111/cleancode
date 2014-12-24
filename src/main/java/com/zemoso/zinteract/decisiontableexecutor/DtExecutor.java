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
		for(DtRow row : dT.getDt()) {
			i = value.entrySet().iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				dtCondition = row.getConditionValues().get(me.getKey());
				comparator = cFactory.getComparator(dtCondition.getComparatorName());
				match = comparator.satisfies(dtCondition,me.getValue().toString());
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
}