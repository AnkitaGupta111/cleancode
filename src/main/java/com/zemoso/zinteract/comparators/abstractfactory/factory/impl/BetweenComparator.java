/**
 * Copy right @Zemoso
 */
package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.BetweenCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * This class is responsible for validating conditions
 * if the condition script is calculating the between operation.
 */
public class BetweenComparator extends Comparator {

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

		BetweenCondition betweenCondition =
				(BetweenCondition) condition;
		Boolean lessThan = ComparatorUtils.isGreaterThan(
				betweenCondition.getLessThanCondition(),
				conditionValue);
		Boolean greaterThan = ComparatorUtils.isLessThan(
				betweenCondition.getGreaterThanCondition(),
				conditionValue);
		if (lessThan == null || greaterThan == null) {
			return false;
		}
		return lessThan && greaterThan;
	}

}
