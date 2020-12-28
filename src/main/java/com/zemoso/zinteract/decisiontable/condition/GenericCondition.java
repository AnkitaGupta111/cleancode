package com.zemoso.zinteract.decisiontable.condition;

import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.util.ComparatorType;
import com.zemoso.zinteract.util.DataType;
import com.zemoso.zinteract.util.PatternMatcher;
import java.util.Optional;
import java.util.regex.Matcher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericCondition extends DecisionTableCondition {

	private ComparatorType comparatorName;

	private String conditionName;

	private DataType dataType;

	private ConditionValue conditionValue;

	@Override
	public Optional<DecisionTableCondition> getMatchingCondition(Matcher matcher, String conditionName,
			String conditionValue, PatternMatcher.patternType type) {

		DecisionTableCondition condition = null;
		matcher.reset(conditionValue);
		if (matcher.find()) {
			String group1 = matcher.group(1);
			String group2 = matcher.group(3);
			if (group1 != null && group2 != null) {
				condition = createGenericCondition(conditionName, group2, PatternMatcher.getDataType(type),
						PatternMatcher.getComparator(type));
			}
		}
		return Optional.ofNullable(condition);

	}

	protected GenericCondition createGenericCondition(String condName, String value, DataType dataType,
			ComparatorType comparatorName) {
		ConditionValue currentConditionValue = new ConditionValue(value);
		GenericCondition genericCondition = new GenericCondition();
		genericCondition.setConditionName(condName);
		genericCondition.setComparatorName(comparatorName);
		genericCondition.setDataType(dataType);
		if (DataType.LONG == dataType) {
			currentConditionValue = new ConditionValue(Long.parseLong(value));
		}
		else if (DataType.DOUBLE == dataType) {
			currentConditionValue = new ConditionValue(Double.parseDouble(value));
		}
		else if (DataType.BOOLEAN == dataType) {
			currentConditionValue = new ConditionValue(Boolean.parseBoolean(value));
		}
		else if (DataType.STRING == dataType) {
			currentConditionValue = new ConditionValue(value);
		}
		genericCondition.setConditionValue(currentConditionValue);
		return genericCondition;
	}

}