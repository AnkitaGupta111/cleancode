package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.InCondition;

public class InComparator extends Comparator {


    @Override
    public Boolean satisfies(DtCondition condition, ConditionValue rhs, boolean ignoreCase) {
        InCondition con = (InCondition) condition;
        boolean matches = false;
        for (GenericCondition gC : con.getInCondition()) {
            Boolean isEqual = ComparatorUtils.isEqual(gC, rhs, ignoreCase);
            if (isEqual == null) {
                break;
            } else if (isEqual) {
                matches = true;
                break;
            }
        }
        return matches;
    }
}