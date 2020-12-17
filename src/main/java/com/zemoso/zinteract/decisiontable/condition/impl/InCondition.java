package com.zemoso.zinteract.decisiontable.condition.impl;

import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.util.StringConstants;
import java.util.List;

/**
 * Created by Praveen on 19-Dec-14.
 */
public class InCondition extends GenericCondition {

	private StringConstants comparatorName;

	private List<GenericCondition> inCondition;

	private String conditionName;

	private StringConstants dataType;

	public void setConditionValue(List<GenericCondition> l) {
		this.inCondition = l;
	}

	public StringConstants getDataType() {
		return this.dataType;
	}

	public void setInCondition(List<GenericCondition> g) {
		this.inCondition = g;
	}

	public List<GenericCondition> getInCondition() {
		return this.inCondition;
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
