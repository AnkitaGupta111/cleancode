package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

/**
 * Created by Praveen on 24-Dec-14.
 */
public abstract class AbstractComparatorFactory {
    public abstract Comparator getComparator(StringConstants keyword);
}
