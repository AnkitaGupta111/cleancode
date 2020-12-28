package com.zemoso.zinteract.decisiontable.condition.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Praveen on 23-Dec-14.
 */
@Getter
@Setter
public class ConditionValue {

	private String stringConditionValue;

	private long longConditionValue;

	private double doubleConditionValue;

	private boolean booleanConditionValue;

	public ConditionValue(long value) {
		this.longConditionValue = value;
	}

	public ConditionValue(double value) {
		this.doubleConditionValue = value;
	}

	public ConditionValue(String value) {
		this.stringConditionValue = value;
	}

	public ConditionValue(boolean value) {
		this.booleanConditionValue = value;
	}

}
