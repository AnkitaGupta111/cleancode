package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

import java.util.HashMap;

public class ComparatorFactory {

	private static HashMap<Enum,Comparator> comparators = new HashMap<Enum, Comparator>();

	static {
		comparators.put(StringConstants.COMPARATOR_GREATERTHAN,new GreaterThanComparator());
		comparators.put(StringConstants.COMPARATOR_GREATERTHAN_EQUALS,new GreaterThanEqualsComparator());
		comparators.put(StringConstants.COMPARATOR_BETWEEN,new BetweenComparator());
		comparators.put(StringConstants.COMPARATOR_LESSTHAN,new LessThanComparator());
		comparators.put(StringConstants.COMPARATOR_LESSTHAN_EQUALS,new LessThanEqualsComparator());
		comparators.put(StringConstants.COMPARATOR_EQUALS,new EqualsComparator());
		comparators.put(StringConstants.COMPARATOR_NOT_EQUALS,new NotEqualsComparator());
		comparators.put(StringConstants.COMPARATOR_IN,new InComparator());
		comparators.put(StringConstants.COMPARATOR_NOTIN,new NotInComparator());
	}

	private static ComparatorFactory cFactory = new ComparatorFactory();

	private ComparatorFactory() {

	}

	public static ComparatorFactory getComparatorFactory() {
		return cFactory;
	}

	public Comparator getComparator(StringConstants keyword) {
		//Find out the appropriate comparator required and return that instance

		return comparators.get(keyword);

	}
}