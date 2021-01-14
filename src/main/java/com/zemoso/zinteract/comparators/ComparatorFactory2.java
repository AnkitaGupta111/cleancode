package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

/**
 * Created by Praveen on 24-Dec-14.
 */
public class ComparatorFactory2 extends AbstractComparatorFactory {

	public ComparatorFactory2() {
		super();
		comparators.put(StringConstants.COMPARATOR_GREATERTHAN, new GreaterThanComparator());

	}

	public Comparator getComparator(StringConstants keyword) {
		// Find out the appropriate comparator required and return that instance

		return comparators.get(keyword);

	}

}
