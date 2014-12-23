package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class EqualsComparator extends Comparator {

	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		GenericCondition eCondition = (GenericCondition) condition;

		if(condition.getDataType() == StringConstants.DATATYPE_DATE){
			return false;
		}
		else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
			return eCondition.getConditionValue().getDoubleConditionValue() == Double.valueOf(rhs).doubleValue();
		}
		else if(condition.getDataType() == StringConstants.DATATYPE_LONG){
			return eCondition.getConditionValue().getLongConditionValue() == Long.valueOf(rhs).longValue();
		}
		else if(condition.getDataType() == StringConstants.DATATYPE_STRING){
			return eCondition.getConditionValue().getStringConditionValue().equals(rhs);
		}

		return false;
	}
}