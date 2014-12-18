package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class EqualsComparator extends Comparator {
	private long l = 2;
	private double d = 5;
	private String s = "a";

	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		GenericCondition eCondition = (GenericCondition) condition;

		if(condition.getDataType() == StringConstants.getDATATYPE_DATE()){
			return false;
		}
		else if(condition.getDataType() == StringConstants.getDATATYPE_DOUBLE()){
			return eCondition.getConditionValue(d) == Double.valueOf(rhs).doubleValue();
		}
		else if(condition.getDataType() == StringConstants.getDATATYPE_LONG()){
			return eCondition.getConditionValue(l) == Long.valueOf(rhs).longValue();
		}
		else if(condition.getDataType() == StringConstants.getDATATYPE_STRING()){
			return eCondition.getConditionValue(s).equals(rhs);
		}

		return false;
	}
}