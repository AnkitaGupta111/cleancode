package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.BetweenCondition;
import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class BetweenComparator extends Comparator {



	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		BetweenCondition bCondition = (BetweenCondition) condition;
		GreaterThanComparator cG = getGreaterThanComparator();
		LessThanComparator cL = getLessThanComparator();
		return cG.satisfies(bCondition.getLessThanConditionValue(),rhs) && cL.satisfies(bCondition.getGreaterThanConditionValue(),rhs);
	}

	private LessThanComparator getLessThanComparator(){
		return (LessThanComparator) ComparatorFactory.getComparatorFactory().getComparator(StringConstants.COMPARATOR_LESSTHAN);
	}

	private GreaterThanComparator getGreaterThanComparator(){
		return (GreaterThanComparator) ComparatorFactory.getComparatorFactory().getComparator(StringConstants.COMPARATOR_GREATERTHAN);
	}
}