package com.zemoso.zinteract.decisiontable;

public abstract class DtCondition {

	public abstract StringConstants getComparatorName();

	public abstract String getConditionName();

	// public abstract String[] getConditionValue();
	public abstract StringConstants getDataType();

}