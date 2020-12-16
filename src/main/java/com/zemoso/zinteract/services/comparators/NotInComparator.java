package com.zemoso.zinteract.services.comparators;

import com.zemoso.zinteract.models.decisiontable.ConditionValue;
import com.zemoso.zinteract.models.decisiontable.DtCondition;
import com.zemoso.zinteract.models.decisiontable.GenericCondition;
import com.zemoso.zinteract.models.decisiontable.InCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotInComparator extends Comparator {
    @Override
    public Boolean satisfies(DtCondition condition,ConditionValue rhs, boolean ignoreCase) {
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
