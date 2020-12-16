package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;

public class GreaterThanEqualsComparator extends Comparator {

    public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {
        Boolean satisfies = ComparatorUtils.isLessThan(condition,rhs);
        if(satisfies == null){
            return false;
        }
        return !satisfies;
    }
}