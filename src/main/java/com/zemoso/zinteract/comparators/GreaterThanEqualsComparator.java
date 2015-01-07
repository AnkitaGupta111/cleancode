package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;

public class GreaterThanEqualsComparator extends Comparator{

    public Boolean satisfies(DtCondition condition,ConditionValue rhs, Boolean ignoreCase) {
        Boolean satisfies = ComparatorUtils.isLessThan(condition,rhs);
        if(satisfies == null){
            return false;
        }
        return !satisfies;
    }
}