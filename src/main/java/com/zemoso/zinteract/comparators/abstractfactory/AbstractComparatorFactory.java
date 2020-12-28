package com.zemoso.zinteract.comparators.abstractfactory;

import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.ComparatorFactory;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.BetweenComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.EqualsComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.GreaterThanComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.GreaterThanEqualsComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.InComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.LessThanComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.LessThanEqualsComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.LikeComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.NotEqualsComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.NotInComparator;
import com.zemoso.zinteract.comparators.abstractfactory.factory.impl.NotLikeComparator;
import com.zemoso.zinteract.util.ComparatorType;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Praveen on 24-Dec-14.
 */
public abstract class AbstractComparatorFactory {

	private static final Logger log = LoggerFactory.getLogger(AbstractComparatorFactory.class);

	@Value("${comparatorFactory.class}")
	private static String comparatorFactoryClass;

	@Value("${default.comparatorFactory.class}")
	private static String defaultComparatorFactoryClass;

	public abstract Comparator getComparator(ComparatorType keyword);

	protected EnumMap<ComparatorType, Comparator> comparators = new EnumMap<>(ComparatorType.class);

	private static AbstractComparatorFactory cFactory = null;

	protected AbstractComparatorFactory() {
		comparators.put(ComparatorType.GREATER_THAN, new GreaterThanComparator());
		comparators.put(ComparatorType.GREATER_THAN_EQUALS, new GreaterThanEqualsComparator());
		comparators.put(ComparatorType.BETWEEN, new BetweenComparator());
		comparators.put(ComparatorType.LESS_THAN, new LessThanComparator());
		comparators.put(ComparatorType.LESS_THAN_EQUALS, new LessThanEqualsComparator());
		comparators.put(ComparatorType.EQUALS, new EqualsComparator());
		comparators.put(ComparatorType.NOT_EQUALS, new NotEqualsComparator());
		comparators.put(ComparatorType.IN, new InComparator());
		comparators.put(ComparatorType.NOT_IN, new NotInComparator());
		comparators.put(ComparatorType.LIKE, new LikeComparator());
		comparators.put(ComparatorType.NOT_LIKE, new NotLikeComparator());
	}

	public static AbstractComparatorFactory getComparatorFactory() {
		if (cFactory != null) {
			return cFactory;
		}
		else {
			synchronized (AbstractComparatorFactory.class) {
				if (cFactory != null) {
					return cFactory;
				}
				if (comparatorFactoryClass == null) {
					comparatorFactoryClass = defaultComparatorFactoryClass;
				}
				try {
					Class<?> c = Class.forName(comparatorFactoryClass);
					cFactory = (AbstractComparatorFactory) c.getDeclaredConstructor().newInstance();
					return cFactory;
				}
				catch (ClassNotFoundException e) {
					log.info("Could not found any class, Initializing default class");
					return new ComparatorFactory();// Default
				}
				catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					log.error("Unable to initialize class");
				}
			}
		}
		return cFactory;
	}

}
