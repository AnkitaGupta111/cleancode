package com.zemoso.zinteract.decisiontable.condition.impl;

import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.util.StringConstants;

public class BetweenCondition extends GenericCondition {

	private StringConstants comparatorName;

	private GenericCondition lessThanCondition;

	private GenericCondition greaterThanCondition;

	private String conditionName;

	private StringConstants dataType;

	public void setConditionValue(GenericCondition lessThanCondition, GenericCondition greaterThanCondition) {
		this.lessThanCondition = lessThanCondition;
		this.greaterThanCondition = greaterThanCondition;
	}

	public StringConstants getDataType() {
		return this.dataType;
	}

	public void setDataType(StringConstants dataType) {
		this.dataType = dataType;
	}

	public GenericCondition getLessThanConditionValue() {
		return lessThanCondition;
	}

	public GenericCondition getGreaterThanConditionValue() {
		return greaterThanCondition;
	}

	public void setLessThanCondition(GenericCondition condition) {
		this.lessThanCondition = condition;
	}

	public void setGreaterThanCondition(GenericCondition condition) {
		this.greaterThanCondition = condition;
	}

	public void setComparatorName(StringConstants comparatorName) {
		this.comparatorName = comparatorName;
	}

	public StringConstants getComparatorName() {
		return comparatorName;
	}

	public String getConditionName() {
		return conditionName;
	}

}