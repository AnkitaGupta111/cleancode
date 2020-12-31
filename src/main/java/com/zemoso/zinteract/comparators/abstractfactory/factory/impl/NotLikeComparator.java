package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * This class is responsible for validating conditions
 * if the condition script is calculating the not like operation.
 */
public class NotLikeComparator extends Comparator {

	/**
	 * Responsible for validating condition value
	 * (input conditionValue) against
	 * the pre defined condition (rules).
	 * @param condition
	 * @param conditionValue
	 * @param ignoreCase
	 * @return
	 */
	@Override
	public Boolean satisfies(
			final DecisionTableCondition condition,
			final ConditionValue conditionValue,
			final boolean ignoreCase) {
		Boolean satisfies = ComparatorUtils.isNotLike(
				condition, conditionValue, ignoreCase);
		if (satisfies == null) {
			return false;
		}
		return satisfies;
	}

}
