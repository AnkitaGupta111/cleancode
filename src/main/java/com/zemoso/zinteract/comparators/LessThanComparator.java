package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class LessThanComparator extends Comparator {
	private long l = 2;
	private double d = 5;

	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		GenericCondition greaterThanCondition = (GenericCondition) condition;

		if(condition.getDataType() == StringConstants.getDATATYPE_DATE()){
			return false;
		}
		else if(condition.getDataType() == StringConstants.getDATATYPE_DOUBLE()){
			return greaterThanCondition.getConditionValue(d) > Double.valueOf(rhs).doubleValue();
		}
		else if(condition.getDataType() == StringConstants.getDATATYPE_LONG()){
			return greaterThanCondition.getConditionValue(l) > Long.valueOf(rhs).longValue();
		}

		return false;
	}
}