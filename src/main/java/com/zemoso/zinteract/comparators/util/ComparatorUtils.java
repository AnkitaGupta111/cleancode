package com.zemoso.zinteract.comparators.util;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.util.DataType;
import java.util.regex.Pattern;

/**
 * Created by Praveen on 24-Dec-14.
 */
public class ComparatorUtils {

	private ComparatorUtils() {

	}

	public static Boolean isEqual(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		GenericCondition eCondition = (GenericCondition) condition;
		if (condition.getDataType() == DataType.STRING) {
			if (eCondition.getConditionValue().getStringConditionValue().equals("*")) {
				return true;
			}
			if (eCondition.getConditionValue().getStringConditionValue().equals("null")) {
				return null == conditionValue.getStringConditionValue() && !conditionValue.isBooleanConditionValue()
						&& conditionValue.getDoubleConditionValue() == 0.0
						&& conditionValue.getLongConditionValue() == 0;
			}
			if (ignoreCase) {
				return eCondition.getConditionValue().getStringConditionValue()
						.equalsIgnoreCase(conditionValue.getStringConditionValue());
			}
			return eCondition.getConditionValue().getStringConditionValue()
					.equals(conditionValue.getStringConditionValue());
		}
		else if (condition.getDataType() == DataType.LONG) {
			return eCondition.getConditionValue().getLongConditionValue() == conditionValue.getLongConditionValue();
		}
		else if (condition.getDataType() == DataType.DOUBLE) {
			return eCondition.getConditionValue().getDoubleConditionValue() == conditionValue.getDoubleConditionValue();
		}
		else if (condition.getDataType() == DataType.BOOLEAN) {
			return eCondition.getConditionValue().isBooleanConditionValue() == conditionValue
					.isBooleanConditionValue();
		}
		else if (condition.getDataType() == DataType.DATE) {
			return false;
		}
		return false;
	}

	public static Boolean isLike(DecisionTableCondition condition, ConditionValue conditionValue, boolean ignoreCase) {
		GenericCondition eCondition = (GenericCondition) condition;
		if (condition.getDataType() == DataType.STRING) {
			if (ignoreCase) {
				return Pattern.compile(Pattern.quote(eCondition.getConditionValue().getStringConditionValue()),
						Pattern.CASE_INSENSITIVE).matcher(conditionValue.getStringConditionValue()).find();
			}
			return Pattern.compile(Pattern.quote(eCondition.getConditionValue().getStringConditionValue()))
					.matcher(conditionValue.getStringConditionValue()).find();
		}
		return false;
	}

	public static Boolean isNotLike(DecisionTableCondition condition, ConditionValue conditionValue,
			boolean ignoreCase) {
		GenericCondition eCondition = (GenericCondition) condition;
		if (condition.getDataType() == DataType.STRING) {
			if (ignoreCase) {
				return !Pattern.compile(Pattern.quote(eCondition.getConditionValue().getStringConditionValue()),
						Pattern.CASE_INSENSITIVE).matcher(conditionValue.getStringConditionValue()).find();
			}
			return !Pattern.compile(Pattern.quote(eCondition.getConditionValue().getStringConditionValue()))
					.matcher(conditionValue.getStringConditionValue()).find();
		}
		return false;
	}

	public static Boolean isGreaterThan(DecisionTableCondition condition, ConditionValue conditionValue) {
		GenericCondition greaterThanCondition = (GenericCondition) condition;

		if (condition.getDataType() == DataType.LONG) {
			return greaterThanCondition.getConditionValue().getLongConditionValue() < conditionValue
					.getLongConditionValue();
		}
		else if (condition.getDataType() == DataType.DOUBLE) {
			return greaterThanCondition.getConditionValue().getDoubleConditionValue() < conditionValue
					.getDoubleConditionValue();
		}
		return false;
	}

	public static Boolean isLessThan(DecisionTableCondition condition, ConditionValue conditionValue) {
		boolean isLessThan = false;
		if (condition.getDataType() == DataType.LONG) {
			isLessThan = condition.getConditionValue().getLongConditionValue() > conditionValue.getLongConditionValue();
		}
		else if (condition.getDataType() == DataType.DOUBLE) {
			isLessThan = condition.getConditionValue().getDoubleConditionValue() > conditionValue
					.getDoubleConditionValue();
		}
		else if (condition.getDataType() == DataType.DATE) {
			return false;
		}
		return isLessThan;
	}

}
