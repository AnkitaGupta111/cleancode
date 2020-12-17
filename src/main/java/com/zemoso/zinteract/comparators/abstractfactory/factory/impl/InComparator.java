package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

public class InComparator extends Comparator {

	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		InCondition con = (InCondition) condition;
		Boolean matches = false;
		for (GenericCondition gC : con.getInCondition()) {
			Boolean isEqual = ComparatorUtils.isEqual(gC, conditionValue, ignoreCase);
			if (isEqual == null) {
				matches = false;
				break;
			}
			else if (isEqual) {
				matches = true;
				break;
			}
		}
		return matches;
	}

}