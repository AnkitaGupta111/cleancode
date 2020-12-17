package com.zemoso.zinteract.decisiontable.condition;

import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.util.StringConstants;

public abstract class DecisionTableCondition {

	public abstract StringConstants getComparatorName();

	public abstract String getConditionName();

	public abstract ConditionValue getConditionValue();

	public abstract StringConstants getDataType();

}