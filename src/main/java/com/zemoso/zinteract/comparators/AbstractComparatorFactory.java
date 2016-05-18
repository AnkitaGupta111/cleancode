package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

import java.util.HashMap;

/**
 * Created by Praveen on 24-Dec-14.
 */
public abstract class AbstractComparatorFactory {

    private static final String PROPERTY_KEY_FOR_COMPARATORYFACTORY_CLASSNAME = "comparatorfactory_class_name";
    private static final String DEFAULT_COMPARATORYFACTORY_CLASSNAME = "com.zemoso.zinteract.comparators.ComparatorFactory";
    public abstract Comparator getComparator(StringConstants keyword);

    protected HashMap<Enum,Comparator> comparators = new HashMap<Enum, Comparator>();

    private static AbstractComparatorFactory cFactory = null;

    protected AbstractComparatorFactory() {
        comparators.put(StringConstants.COMPARATOR_GREATERTHAN,new GreaterThanComparator());
        comparators.put(StringConstants.COMPARATOR_GREATERTHAN_EQUALS,new GreaterThanEqualsComparator());
        comparators.put(StringConstants.COMPARATOR_BETWEEN,new BetweenComparator());
        comparators.put(StringConstants.COMPARATOR_LESSTHAN,new LessThanComparator());
        comparators.put(StringConstants.COMPARATOR_LESSTHAN_EQUALS,new LessThanEqualsComparator());
        comparators.put(StringConstants.COMPARATOR_EQUALS,new EqualsComparator());
        comparators.put(StringConstants.COMPARATOR_NOT_EQUALS,new NotEqualsComparator());
        comparators.put(StringConstants.COMPARATOR_IN,new InComparator());
        comparators.put(StringConstants.COMPARATOR_NOTIN,new NotInComparator());
        comparators.put(StringConstants.COMPARATOR_LIKE,new LIKECOMPARATOR());
        comparators.put(StringConstants.COMPARATOR_NOT_LIKE,new NOTLIKECOMPARATOR());
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
                String className = System.getProperty(PROPERTY_KEY_FOR_COMPARATORYFACTORY_CLASSNAME);
                if(className == null){
                    className = DEFAULT_COMPARATORYFACTORY_CLASSNAME;
                }
                try {
                    Class c = Class.forName(className);
                    cFactory = (AbstractComparatorFactory)c.newInstance();
                    return cFactory;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return new ComparatorFactory();//Default
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return cFactory;
    }
}
