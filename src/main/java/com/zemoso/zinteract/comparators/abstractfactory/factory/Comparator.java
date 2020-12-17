package com.zemoso.zinteract.comparators.abstractfactory.factory;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;

public abstract class Comparator {

	public abstract Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase);

}