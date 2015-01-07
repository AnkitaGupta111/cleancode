package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.*;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotInComparator extends Comparator {
    @Override
    public Boolean satisfies(DtCondition condition,ConditionValue rhs, StringConstants caseSensitivity) {
        InCondition con = (InCondition) condition;
        Boolean matches = true;
        for(GenericCondition gC : con.getInCondition()){
            Boolean isEqual = ComparatorUtils.isEqual(gC,rhs,caseSensitivity);
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
