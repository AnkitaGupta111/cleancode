package com.zemoso.zinteract.comparators.abstractfactory.factory;

import com.zemoso.zinteract.comparators.abstractfactory.AbstractComparatorFactory;
import com.zemoso.zinteract.util.StringConstants;

public class ComparatorFactory extends AbstractComparatorFactory {

	public Comparator getComparator(StringConstants keyword) {
		// Find out the appropriate comparator required and return that instance

		return comparators.get(keyword);

	}

}