package com.zemoso.zinteract.decisiontable.condition;

import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.util.DataType;
import com.zemoso.zinteract.util.ComparatorType;
import com.zemoso.zinteract.util.PatternMatcher;
import java.util.Optional;
import java.util.regex.Matcher;

public abstract class DecisionTableCondition {

	public abstract ComparatorType getComparatorName();

	public abstract String getConditionName();

	public abstract ConditionValue getConditionValue();

	public abstract DataType getDataType();

  public abstract Optional<DecisionTableCondition> getMatchingCondition(
  		Matcher matcher, String conditionName, String conditionValue, PatternMatcher.patternType type);


}