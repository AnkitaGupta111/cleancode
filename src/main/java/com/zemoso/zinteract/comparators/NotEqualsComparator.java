package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class NotEqualsComparator extends Comparator {

	@Override
	public Boolean satisfies(DtCondition condition, ConditionValue rhs, boolean ignoreCase) {
		Boolean satisfies = ComparatorUtils.isEqual(condition, rhs, ignoreCase);
		if (satisfies == null) {
			return false;
		}
		return !satisfies;
	}

}
