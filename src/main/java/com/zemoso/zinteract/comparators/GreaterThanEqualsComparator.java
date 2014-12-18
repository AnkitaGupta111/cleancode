package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class GreaterThanEqualsComparator extends Comparator {

    private long l = 2;
    private double d = 5;

    @Override
    public Boolean satisfies(DtCondition condition,String rhs) {
        return getGreaterThanComparator().satisfies(condition,rhs) || getEqualsComparator().satisfies(condition, rhs);
    }

    private GreaterThanComparator getGreaterThanComparator() {
        return (GreaterThanComparator)ComparatorFactory.getComparatorFactory().getComparator(StringConstants.getCOMPARATOR_GREATERTHAN());
    }

    private EqualsComparator getEqualsComparator(){
        return (EqualsComparator) ComparatorFactory.getComparatorFactory().getComparator(StringConstants.getCOMPARATOR_EQUALS());
    }
}