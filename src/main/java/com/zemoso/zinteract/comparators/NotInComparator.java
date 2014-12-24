package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.InCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotInComparator extends Comparator {
    @Override
    public Boolean satisfies(DtCondition condition,String rhs) {
        InCondition con = (InCondition) condition;
        Boolean matches = true;
        for(GenericCondition gC : con.getInCondition()){
            if(ComparatorUtils.isEqual(gC,rhs)){
                matches = false;
                break;
            }
        }
        return matches;
    }
}
