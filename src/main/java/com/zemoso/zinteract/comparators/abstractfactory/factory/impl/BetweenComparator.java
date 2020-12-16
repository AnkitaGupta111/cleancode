package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.impl.BetweenCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;

public class BetweenComparator extends Comparator {



	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {

		BetweenCondition bCondition = (BetweenCondition) condition;
		Boolean lessThan = ComparatorUtils.isGreaterThan(bCondition.getLessThanConditionValue(),rhs);
		Boolean greaterThan = ComparatorUtils.isLessThan(bCondition.getGreaterThanConditionValue(),rhs);
		if(lessThan == null || greaterThan == null){
			return false;
		}
		return lessThan && greaterThan;
	}
}