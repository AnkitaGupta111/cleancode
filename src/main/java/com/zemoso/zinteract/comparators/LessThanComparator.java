package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public class LessThanComparator extends Comparator {

	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		Boolean satisfies = ComparatorUtils.isLessThan(condition,rhs);
		if(satisfies == null){
			return false;
		}
		return satisfies;
	}
}