package com.zemoso.zinteract.comparators;


import com.zemoso.zinteract.decisiontable.DtCondition;

public abstract class Comparator {


	public abstract Boolean satisfies(DtCondition condition, String rhs);
}