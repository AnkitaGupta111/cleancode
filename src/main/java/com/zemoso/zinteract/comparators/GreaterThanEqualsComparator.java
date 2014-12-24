package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class GreaterThanEqualsComparator extends Comparator{

    public Boolean satisfies(DtCondition condition,String rhs) {
        Boolean satisfies = ComparatorUtils.isLessThan(condition,rhs);
        if(satisfies == null){
            return false;
        }
        return !satisfies;
    }
}