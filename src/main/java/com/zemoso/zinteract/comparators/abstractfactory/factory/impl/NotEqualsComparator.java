package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotEqualsComparator extends Comparator {

	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		Boolean satisfies = ComparatorUtils.isEqual(condition, conditionValue, ignoreCase);
		if (satisfies == null) {
			return false;
		}
		return !satisfies;
	}

}
