package com.zemoso.zinteract.services.comparators;


import com.zemoso.zinteract.models.decisiontable.ConditionValue;
import com.zemoso.zinteract.models.decisiontable.DtCondition;

public abstract class Comparator {


	public abstract Boolean satisfies(DtCondition condition, ConditionValue rhs, boolean ignoreCase);
}