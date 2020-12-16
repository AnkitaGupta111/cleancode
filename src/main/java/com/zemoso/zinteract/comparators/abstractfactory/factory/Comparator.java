package com.zemoso.zinteract.comparators.abstractfactory.factory;


import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;

public abstract class Comparator {


	public abstract Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase);
}