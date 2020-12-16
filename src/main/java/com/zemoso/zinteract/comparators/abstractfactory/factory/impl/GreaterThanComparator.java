package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;

public class GreaterThanComparator extends Comparator {

	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {

		Boolean satisfies = ComparatorUtils.isGreaterThan(condition,rhs);
		if(satisfies == null){
			return false;
		}
		return satisfies;
	}
}