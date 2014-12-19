package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotInComparator extends InComparator {
    @Override
    public Boolean satisfies(DtCondition condition,String rhs) {
        return !super.satisfies(condition,rhs);
    }
}
