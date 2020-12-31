package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * This class is responsible for validating conditions
 * if the condition script is calculating the Greater than and equals operation.
 */
public class GreaterThanEqualsComparator extends Comparator {

	/**
	 * Responsible for validating condition value
	 * (input conditionValue) against
	 * the pre defined condition (rules).
	 * @param condition
	 * @param conditionValue
	 * @param ignoreCase
	 * @return
	 */
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		Boolean satisfies = ComparatorUtils.isLessThan(condition, conditionValue);
		if (satisfies == null) {
			return false;
		}
		return !satisfies;
	}

}