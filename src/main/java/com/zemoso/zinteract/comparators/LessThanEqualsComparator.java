package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class LessThanEqualsComparator extends Comparator {

    @Override
    public Boolean satisfies(DtCondition condition,String rhs) {
        return getLessThanComparator().satisfies(condition,rhs) || getEqualsComparator().satisfies(condition,rhs);
    }

    private LessThanComparator getLessThanComparator() {
        return (LessThanComparator)ComparatorFactory.getComparatorFactory().getComparator(StringConstants.COMPARATOR_LESSTHAN);
    }

    private EqualsComparator getEqualsComparator(){
        return (EqualsComparator) ComparatorFactory.getComparatorFactory().getComparator(StringConstants.COMPARATOR_EQUALS);
    }
}