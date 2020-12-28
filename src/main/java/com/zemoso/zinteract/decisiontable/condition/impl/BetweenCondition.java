package com.zemoso.zinteract.decisiontable.condition.impl;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.util.ComparatorType;
import com.zemoso.zinteract.util.DataType;
import com.zemoso.zinteract.util.PatternMatcher;
import java.util.Optional;
import java.util.regex.Matcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BetweenCondition extends GenericCondition {

	private ComparatorType comparatorName;

	private GenericCondition lessThanCondition;

	private GenericCondition greaterThanCondition;

	private String conditionName;

	private DataType dataType;



	@Override
	public Optional<DecisionTableCondition> getMatchingCondition(Matcher matcher, String conditionName,
			String conditionValue, PatternMatcher.patternType type) {

		BetweenCondition betweenCondition = null;
		matcher.reset(conditionValue);
		if (matcher.find()) {
			String group1 = matcher.group(1);
			if (group1 != null && matcher.group(3) != null && matcher.group(5) != null && matcher.group(7) != null) {
				GenericCondition lessThan = createGenericCondition(conditionName, matcher.group(7), PatternMatcher.getDataType(type),
						ComparatorType.LESS_THAN);
				GenericCondition greatThan = createGenericCondition(conditionName, matcher.group(3), PatternMatcher.getDataType(type),
						ComparatorType.GREATER_THAN);
				betweenCondition= BetweenCondition.builder()
						.lessThanCondition(lessThan)
						.greaterThanCondition(greatThan)
						.dataType(PatternMatcher.getDataType(type))
						.conditionName(conditionName)
						.comparatorName(PatternMatcher.getComparator(type))
						.build();
				}
		}
		return Optional.ofNullable(betweenCondition);
	}



}