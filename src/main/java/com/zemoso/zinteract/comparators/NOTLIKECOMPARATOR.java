package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;

public class NOTLIKECOMPARATOR extends Comparator {

    @Override
    public Boolean satisfies(DtCondition condition, ConditionValue rhs, boolean ignoreCase) {
        Boolean satisfies = ComparatorUtils.isNotLike(condition, rhs, ignoreCase);
        if (satisfies == null) {
            return false;
        }
        return satisfies;
    }
}