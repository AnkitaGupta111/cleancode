package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * This class is responsible for validating conditions
 * if the condition script is calculating the not in operation.
 */
public class NotInComparator extends Comparator {

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
		InCondition inCondition = (InCondition) condition;
		for (GenericCondition genericCondition
				: inCondition.getConditions()) {
			Boolean isEqual = ComparatorUtils.isEqual(
					genericCondition,
					conditionValue,
					ignoreCase);
			if (isEqual) {
				return false;
			}
		}
		return true;
	}

}
