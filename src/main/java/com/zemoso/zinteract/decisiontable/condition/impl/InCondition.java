package com.zemoso.zinteract.decisiontable.condition.impl;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.util.ComparatorType;
import com.zemoso.zinteract.util.DataType;
import com.zemoso.zinteract.util.PatternMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Praveen on 19-Dec-14.
 */
@Getter
@Setter
public class InCondition extends GenericCondition {

	private ComparatorType comparatorName;

	private List<GenericCondition> conditions;

	private String conditionName;

	private DataType dataType;

	@Override
	public Optional<DecisionTableCondition> getMatchingCondition(Matcher matcher, String conditionName,
			String conditionValue, PatternMatcher.patternType type) {
		InCondition inCondition = new InCondition();
		matcher.reset(conditionValue);
		if (matcher.find()) {
			String group1 = matcher.group(1);
			String group2 = matcher.group(3);
			String group5 = matcher.group(5);
			if (group1 != null && group2 != null && group5 != null) {
				String[] longs = group5.split(",");
				List<GenericCondition> list = new ArrayList<>();
				GenericCondition finalCondition;
				for (String s : longs) {
					finalCondition = createGenericCondition(conditionName, s, PatternMatcher.getDataType(type),
							PatternMatcher.getComparator(type));
					list.add(finalCondition);
				}
				inCondition.setComparatorName(PatternMatcher.getComparator(type));
				inCondition.setConditionName(conditionName);
				inCondition.setDataType(PatternMatcher.getDataType(type));
				inCondition.setConditions(list);
			}
			else if (group1 != null && group2 != null) {
				String[] longs = matcher.group(3).split(",");
				List<GenericCondition> list = new ArrayList<>();
				GenericCondition finalCondition;
				for (String s : longs) {
					finalCondition = createGenericCondition(conditionName, s, PatternMatcher.getDataType(type),
							PatternMatcher.getComparator(type));
					list.add(finalCondition);
				}
				inCondition.setComparatorName(PatternMatcher.getComparator(type));
				inCondition.setConditionName(conditionName);
				inCondition.setDataType(PatternMatcher.getDataType(type));
				inCondition.setConditions(list);
			}
		}
		return Optional.of(inCondition);
	}

}
