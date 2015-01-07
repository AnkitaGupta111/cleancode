package com.zemoso.zinteract.comparators;


import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

public abstract class Comparator {


	public abstract Boolean satisfies(DtCondition condition, ConditionValue rhs, Boolean ignoreCase);
}