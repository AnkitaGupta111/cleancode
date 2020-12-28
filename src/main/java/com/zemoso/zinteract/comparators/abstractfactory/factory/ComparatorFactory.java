package com.zemoso.zinteract.comparators.abstractfactory.factory;

import com.zemoso.zinteract.comparators.abstractfactory.AbstractComparatorFactory;
import com.zemoso.zinteract.util.ComparatorType;

public class ComparatorFactory extends AbstractComparatorFactory {

	public Comparator getComparator(ComparatorType keyword) {
		return comparators.get(keyword);
	}
}