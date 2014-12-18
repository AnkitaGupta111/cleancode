package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;

public class InComparator extends Comparator {
	@Override
	public Boolean satisfies(DtCondition condition,String rhs) {
		return true;
	}
}