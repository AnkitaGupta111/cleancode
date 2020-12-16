package com.zemoso.zinteract.services.comparators;

import com.zemoso.zinteract.models.decisiontable.ConditionValue;
import com.zemoso.zinteract.models.decisiontable.DtCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotEqualsComparator extends Comparator {
    @Override
    public Boolean satisfies(DtCondition condition,ConditionValue rhs, boolean ignoreCase) {
        Boolean satisfies = ComparatorUtils.isEqual(condition,rhs,ignoreCase);
        if(satisfies == null){
            return false;
        }
        return !satisfies;
    }
}
