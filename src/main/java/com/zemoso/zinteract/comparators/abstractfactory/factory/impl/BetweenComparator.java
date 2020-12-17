package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.BetweenCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

public class BetweenComparator extends Comparator {

	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {

		BetweenCondition betweenCondition = (BetweenCondition) condition;
		Boolean lessThan = ComparatorUtils.isGreaterThan(betweenCondition.getLessThanConditionValue(), conditionValue);
		Boolean greaterThan = ComparatorUtils.isLessThan(betweenCondition.getGreaterThanConditionValue(), conditionValue);
		if (lessThan == null || greaterThan == null) {
			return false;
		}
		return lessThan && greaterThan;
	}

}