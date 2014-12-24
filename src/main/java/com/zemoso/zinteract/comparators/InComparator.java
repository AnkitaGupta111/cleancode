package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.InCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class InComparator extends Comparator {


	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		InCondition con = (InCondition) condition;
		Boolean matches = false;
		for(GenericCondition gC : con.getInCondition()){
			if(ComparatorUtils.isEqual(gC,rhs)){
				matches = true;
				break;
			}
		}
		return matches;
	}
}