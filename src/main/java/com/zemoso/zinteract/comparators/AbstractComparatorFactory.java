package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.constants.Constant;
import com.zemoso.zinteract.decisiontable.StringConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Praveen on 24-Dec-14.
 */
@Slf4j
public abstract class AbstractComparatorFactory {

    private static final String DEFAULT_COMPARATOR_FACTORY_CLASS_NAME = Constant.COMPARATOR_CLASS_NAME;
    private static AbstractComparatorFactory cFactory = null;
    protected Map<Enum<?>, Comparator> comparators = new HashMap<>();

    protected AbstractComparatorFactory() {
        comparators.put(StringConstants.COMPARATOR_GREATERTHAN, new GreaterThanComparator());
        comparators.put(StringConstants.COMPARATOR_GREATERTHAN_EQUALS, new GreaterThanEqualsComparator());
        comparators.put(StringConstants.COMPARATOR_BETWEEN, new BetweenComparator());
        comparators.put(StringConstants.COMPARATOR_LESSTHAN, new LessThanComparator());
        comparators.put(StringConstants.COMPARATOR_LESSTHAN_EQUALS, new LessThanEqualsComparator());
        comparators.put(StringConstants.COMPARATOR_EQUALS, new EqualsComparator());
        comparators.put(StringConstants.COMPARATOR_NOT_EQUALS, new NotEqualsComparator());
        comparators.put(StringConstants.COMPARATOR_IN, new InComparator());
        comparators.put(StringConstants.COMPARATOR_NOTIN, new NotInComparator());
        comparators.put(StringConstants.COMPARATOR_LIKE, new LikeComparator());
        comparators.put(StringConstants.COMPARATOR_NOT_LIKE, new NotLikeComparator());
    }

    public static AbstractComparatorFactory getComparatorFactory() {
        if (cFactory != null) {
            return cFactory;
        } else {
            synchronized (AbstractComparatorFactory.class) {
                if (cFactory != null) {
                    return cFactory;
                }
                try {
                    Class<?> c = Class.forName(DEFAULT_COMPARATOR_FACTORY_CLASS_NAME);
                    cFactory = (AbstractComparatorFactory) c.newInstance();
                    return cFactory;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return cFactory;
    }

    public abstract Comparator getComparator(StringConstants keyword);
}
