package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

public class ComparatorFactory extends AbstractComparatorFactory{

	public Comparator getComparator(StringConstants keyword) {
		//Find out the appropriate comparator required and return that instance

		return comparators.get(keyword);

	}
}