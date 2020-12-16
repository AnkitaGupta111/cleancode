package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotInComparator extends Comparator {
    @Override
    public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {
        InCondition con = (InCondition) condition;
        Boolean matches = true;
        for(GenericCondition gC : con.getInCondition()){
            Boolean isEqual = ComparatorUtils.isEqual(gC,rhs,ignoreCase);
            if(isEqual == null){
                matches = false;
                break;
            }
            if(isEqual){
                matches = false;
                break;
            }
        }
        return matches;
    }
}
