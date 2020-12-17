package com.zemoso.zinteract.decisiontable.condition.model;

/**
 * Created by Praveen on 23-Dec-14.
 */
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

	public String getStringConditionValue() {
		return stringConditionValue;
	}

	public void setStringConditionValue(String stringConditionValue) {
		this.stringConditionValue = stringConditionValue;
	}

	public long getLongConditionValue() {
		return longConditionValue;
	}

	public void setLongConditionValue(long longConditionValue) {
		this.longConditionValue = longConditionValue;
	}

	public double getDoubleConditionValue() {
		return doubleConditionValue;
	}

	public void setDoubleConditionValue(double doubleConditionValue) {
		this.doubleConditionValue = doubleConditionValue;
	}

	public boolean getBooleanConditionValue() {
		return this.booleanConditionValue;
	}

	public void setBooleanConditionValue(boolean booleanConditionValue) {
		this.booleanConditionValue = booleanConditionValue;
	}

}
