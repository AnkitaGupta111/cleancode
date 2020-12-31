package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * This class is responsible for validating conditions
 * if the condition script is calculating the In operation.
 */
public class InComparator extends Comparator {

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
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		InCondition con = (InCondition) condition;
		for (GenericCondition gC : con.getConditions()) {
			Boolean isEqual = ComparatorUtils.isEqual(gC, conditionValue, ignoreCase);
			if (isEqual) {
				return true;
			}
		}
		return false;
	}

}