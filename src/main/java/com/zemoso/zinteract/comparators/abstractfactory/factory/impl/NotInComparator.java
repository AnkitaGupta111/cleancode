package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotInComparator extends Comparator {

	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		InCondition inCondition = (InCondition) condition;
		for (GenericCondition genericCondition : inCondition.getConditions()) {
			Boolean isEqual = ComparatorUtils.isEqual(genericCondition, conditionValue, ignoreCase);
			if (isEqual) {
				return false;
			}
		}
		return true;
	}

}
