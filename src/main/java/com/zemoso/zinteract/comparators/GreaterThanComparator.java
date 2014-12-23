package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class GreaterThanComparator extends Comparator {

	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		GenericCondition greaterThanCondition = (GenericCondition) condition;

		if(condition.getDataType() == StringConstants.DATATYPE_LONG){
			return greaterThanCondition.getConditionValue().getLongConditionValue() < Long.valueOf(rhs).longValue();
		}
		else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
			return greaterThanCondition.getConditionValue().getDoubleConditionValue() < Double.valueOf(rhs).doubleValue();
		}
		if(condition.getDataType() == StringConstants.DATATYPE_DATE){
			return false;
		}
		return false;
	}
}