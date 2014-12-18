package com.zemoso.zinteract.comparators;


import com.zemoso.zinteract.decisiontable.DtCondition;

public abstract class Comparator {


	public abstract Boolean satisfies(DtCondition condition, String rhs);
//	public abstract Boolean satisfies(DtCondition condition, Date rhs);
//	public abstract Boolean satisfies(DtCondition condition, double rhs);
//	public abstract Boolean satisfies(DtCondition condition, long rhs);
}