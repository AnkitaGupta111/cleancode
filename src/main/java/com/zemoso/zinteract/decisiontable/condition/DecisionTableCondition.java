package com.zemoso.zinteract.decisiontable.condition;

import com.zemoso.zinteract.util.StringConstants;

public abstract class DecisionTableCondition {
	public abstract StringConstants getComparatorName();
	public abstract String getConditionName();
	//public abstract String[] getConditionValue();
	public abstract StringConstants getDataType();

}