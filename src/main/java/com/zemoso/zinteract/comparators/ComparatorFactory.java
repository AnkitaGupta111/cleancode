package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

import java.util.HashMap;

public class ComparatorFactory extends AbstractComparatorFactory{

	protected HashMap<Enum,Comparator> comparators = new HashMap<Enum, Comparator>();

	private static AbstractComparatorFactory cFactory = null;

	protected ComparatorFactory() {
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

	public static AbstractComparatorFactory getComparatorFactory() {
		if(cFactory != null){
			return cFactory;
		}
		else {
			synchronized (AbstractComparatorFactory.class){
				if(cFactory != null){
					return cFactory;
				}
				System.setProperty("comparatorfactory_class_name","com.zemoso.zinteract.comparators.ComparatorFactory");
				String className = System.getProperty("comparatorfactory_class_name");
				try {
					Class c = Class.forName(className);
					cFactory = (AbstractComparatorFactory)c.newInstance();
					return cFactory;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return cFactory;
	}

	public Comparator getComparator(StringConstants keyword) {
		//Find out the appropriate comparator required and return that instance

		return comparators.get(keyword);

	}
}