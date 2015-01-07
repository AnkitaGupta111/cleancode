package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.BetweenCondition;
import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;

public class BetweenComparator extends Comparator {



	@Override
	public Boolean satisfies(DtCondition condition,ConditionValue rhs, Boolean ignoreCase) {

		BetweenCondition bCondition = (BetweenCondition) condition;
		Boolean lessThan = ComparatorUtils.isGreaterThan(bCondition.getLessThanConditionValue(),rhs);
		Boolean greaterThan = ComparatorUtils.isLessThan(bCondition.getGreaterThanConditionValue(),rhs);
		if(lessThan == null || greaterThan == null){
			return false;
		}
		return lessThan && greaterThan;
	}
}