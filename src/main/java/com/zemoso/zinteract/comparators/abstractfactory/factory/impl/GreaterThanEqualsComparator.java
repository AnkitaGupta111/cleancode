package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

public class GreaterThanEqualsComparator extends Comparator {

	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		Boolean satisfies = ComparatorUtils.isLessThan(condition, conditionValue);
		if (satisfies == null) {
			return false;
		}
		return !satisfies;
	}

}