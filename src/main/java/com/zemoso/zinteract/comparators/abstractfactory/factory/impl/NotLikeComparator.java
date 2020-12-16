package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;

public class NotLikeComparator extends Comparator {

	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {
		Boolean satisfies = ComparatorUtils.isNotLike(condition,rhs,ignoreCase);
		if(satisfies == null){
			return false;
		}
		return satisfies;
	}
}