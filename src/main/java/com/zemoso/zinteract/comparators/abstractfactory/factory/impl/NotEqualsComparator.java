package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotEqualsComparator extends Comparator {
    @Override
    public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {
        Boolean satisfies = ComparatorUtils.isEqual(condition,rhs,ignoreCase);
        if(satisfies == null){
            return false;
        }
        return !satisfies;
    }
}
