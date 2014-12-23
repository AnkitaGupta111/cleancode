package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class GreaterThanEqualsComparator extends LessThanComparator {

    @Override
    public Boolean satisfies(DtCondition condition,String rhs) {
        return !super.satisfies(condition,rhs);
    }
}