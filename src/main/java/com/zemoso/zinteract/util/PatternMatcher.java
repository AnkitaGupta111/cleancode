package com.zemoso.zinteract.util;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.model.PatternCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.BetweenCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Praveen on 23-Dec-14.
 */
public class PatternMatcher {

	public static Map<patternType, PatternCondition> matchers = new LinkedHashMap<>();

	public static Map<patternType, DataType> matcherDataTypes = new HashMap<>();

	public static Map<patternType, ComparatorType> matcherComparatorTypes = new LinkedHashMap<>();


	public enum patternType {

		GREATERTHANLONGPATTERN, LESSTHANLONGPATTERN, EQUALSLONGPATTERN, NOTEQUALSLONGPATTERN, LESSTHANEQUALSLONGPATTERN, GREATERTHANDOUBLEPATTERN, EQUALSDOUBLEPATTERN, NOTEQUALSDOUBLEPATTERN, GREATERTHANEQUALSDOUBLEPATTERN, LESSTHANDOUBLEPATTERN, LESSTHANEQUALSDOUBLEPATTERN, GREATERTHANEQUALSLONGPATTERN, BETWEENLONGPATTERN, BETWEENDOUBLEPATTERN, INLONGPATTERN, NOTINLONGPATTERN, INDOUBLEPATTERN, NOTINDOUBLEPATTERN, NOTEQUALSSTRINGPATTERN, INSTRINGPATTERN, NOTINSTRINGPATTERN, EQUALSSTRINGPATTERN, EQUALSBOOLEANPATTERN, NOTLIKESTRINGPATTERN, LIKESTRINGPATTERN

	}

	static {
		matchers.put(patternType.GREATERTHANLONGPATTERN,
				new PatternCondition(Pattern.compile("(^>)(\\s*)(\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.LESSTHANLONGPATTERN,
				new PatternCondition(Pattern.compile("(^<)(\\s*)(\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.EQUALSLONGPATTERN,
				new PatternCondition(Pattern.compile("(^=*)(\\s*)(\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.NOTEQUALSLONGPATTERN,
				new PatternCondition(Pattern.compile("(^!=+)(\\s*)(\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.LESSTHANEQUALSLONGPATTERN,
				new PatternCondition(Pattern.compile("(^<\\s*=+)(\\s*)(\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.GREATERTHANDOUBLEPATTERN,
				new PatternCondition(Pattern.compile("(^>)(\\s*)(\\d+\\.\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.EQUALSDOUBLEPATTERN,
				new PatternCondition(Pattern.compile("(^=*)(\\s*)(\\d+\\.\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.NOTEQUALSDOUBLEPATTERN, new PatternCondition(
				Pattern.compile("(^!=+)(\\s*)(\\d+\\.\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.GREATERTHANEQUALSDOUBLEPATTERN, new PatternCondition(
				Pattern.compile("(^>\\s*=+)(\\s*)(\\d+\\.\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.LESSTHANDOUBLEPATTERN,
				new PatternCondition(Pattern.compile("(^<)(\\s*)(\\d+\\.\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.LESSTHANEQUALSDOUBLEPATTERN, new PatternCondition(
				Pattern.compile("(^<\\s*=+)(\\s*)(\\d+\\.\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.GREATERTHANEQUALSLONGPATTERN,
				new PatternCondition(Pattern.compile("(^>\\s*=+)(\\s*)(\\d+$)").matcher(""), new GenericCondition()));
		matchers.put(patternType.BETWEENLONGPATTERN, new PatternCondition(
				Pattern.compile("(^between)(\\s*)(\\d+)(\\s*)(and)(\\s*)(\\d+$)").matcher(""), new BetweenCondition()));
		matchers.put(patternType.BETWEENDOUBLEPATTERN,
				new PatternCondition(
						Pattern.compile("(^between)(\\s*)(\\d+\\.\\d+)(\\s*)(and)(\\s*)(\\d+\\.\\d+$)").matcher(""),
						new BetweenCondition()));
		matchers.put(patternType.INLONGPATTERN, new PatternCondition(
				Pattern.compile("(^in)(\\s*)(\\d+,\\d+){1,}").matcher(""), new InCondition()));
		matchers.put(patternType.NOTINLONGPATTERN, new PatternCondition(
				Pattern.compile("(^not)(\\s*)(in)(\\s*)(\\d+,\\d+){1,}").matcher(""), new InCondition()));
		matchers.put(patternType.INDOUBLEPATTERN, new PatternCondition(
				Pattern.compile("(^in)(\\s*)(\\d+\\.\\d+,\\d+\\.\\d+){1,}").matcher(""), new InCondition()));
		matchers.put(patternType.NOTINDOUBLEPATTERN,
				new PatternCondition(Pattern.compile("(^not)(\\s*)(in)(\\s*)(\\d+\\.\\d+,\\d+\\.\\d+){1,}").matcher(""),
						new InCondition()));
		matchers.put(patternType.NOTEQUALSSTRINGPATTERN,
				new PatternCondition(Pattern.compile("(^!=+)(\\s*)(.*)").matcher(""), new GenericCondition()));
		matchers.put(patternType.INSTRINGPATTERN,
				new PatternCondition(Pattern.compile("(^in)(\\s*)(.*,.*){1,}").matcher(""), new InCondition()));
		matchers.put(patternType.NOTINSTRINGPATTERN, new PatternCondition(
				Pattern.compile("(^not)(\\s*)(in)(\\s*)(.*,.*){1,}").matcher(""), new InCondition()));
		matchers.put(patternType.LIKESTRINGPATTERN,
				new PatternCondition(Pattern.compile("(^~+)(\\s*)(.*)").matcher(""), new GenericCondition()));
		matchers.put(patternType.NOTLIKESTRINGPATTERN,
				new PatternCondition(Pattern.compile("(^!~+)(\\s*)(.*)").matcher(""), new GenericCondition()));
		matchers.put(patternType.EQUALSBOOLEANPATTERN,
				new PatternCondition(Pattern.compile("(^=*)(\\s*)(true|false)", Pattern.CASE_INSENSITIVE).matcher(""),
						new GenericCondition()));
		matchers.put(patternType.EQUALSSTRINGPATTERN,
				new PatternCondition(Pattern.compile("(^=*)(\\s*)(.*)").matcher(""), new GenericCondition()));
	}

	static {
		matcherDataTypes.put(patternType.GREATERTHANLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.LESSTHANLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.EQUALSLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.NOTEQUALSLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.LESSTHANEQUALSLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.GREATERTHANDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.EQUALSDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.NOTEQUALSDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.GREATERTHANEQUALSDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.LESSTHANDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.LESSTHANEQUALSDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.GREATERTHANEQUALSLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.BETWEENLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.BETWEENDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.INLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.NOTINLONGPATTERN, DataType.LONG);
		matcherDataTypes.put(patternType.INDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.NOTINDOUBLEPATTERN, DataType.DOUBLE);
		matcherDataTypes.put(patternType.NOTEQUALSSTRINGPATTERN, DataType.STRING);
		matcherDataTypes.put(patternType.INSTRINGPATTERN, DataType.STRING);
		matcherDataTypes.put(patternType.NOTINSTRINGPATTERN, DataType.STRING);
		matcherDataTypes.put(patternType.EQUALSSTRINGPATTERN, DataType.STRING);
		matcherDataTypes.put(patternType.NOTLIKESTRINGPATTERN, DataType.STRING);
		matcherDataTypes.put(patternType.LIKESTRINGPATTERN, DataType.STRING);
		matcherDataTypes.put(patternType.EQUALSBOOLEANPATTERN, DataType.BOOLEAN);

	}

	static {
		matcherComparatorTypes.put(patternType.GREATERTHANLONGPATTERN, ComparatorType.GREATER_THAN);
		matcherComparatorTypes.put(patternType.LESSTHANLONGPATTERN, ComparatorType.LESS_THAN);
		matcherComparatorTypes.put(patternType.EQUALSLONGPATTERN, ComparatorType.EQUALS);
		matcherComparatorTypes.put(patternType.NOTEQUALSLONGPATTERN, ComparatorType.NOT_EQUALS);
		matcherComparatorTypes.put(patternType.LESSTHANEQUALSLONGPATTERN, ComparatorType.LESS_THAN_EQUALS);
		matcherComparatorTypes.put(patternType.GREATERTHANDOUBLEPATTERN,ComparatorType.GREATER_THAN);
		matcherComparatorTypes.put(patternType.EQUALSDOUBLEPATTERN, ComparatorType.EQUALS);
		matcherComparatorTypes.put(patternType.NOTEQUALSDOUBLEPATTERN, ComparatorType.NOT_EQUALS);
		matcherComparatorTypes.put(patternType.GREATERTHANEQUALSDOUBLEPATTERN, ComparatorType.GREATER_THAN_EQUALS);
		matcherComparatorTypes.put(patternType.LESSTHANDOUBLEPATTERN, ComparatorType.LESS_THAN);
		matcherComparatorTypes.put(patternType.LESSTHANEQUALSDOUBLEPATTERN, ComparatorType.LESS_THAN_EQUALS);
		matcherComparatorTypes.put(patternType.GREATERTHANEQUALSLONGPATTERN, ComparatorType.GREATER_THAN_EQUALS);
		matcherComparatorTypes.put(patternType.BETWEENLONGPATTERN, ComparatorType.BETWEEN);
		matcherComparatorTypes.put(patternType.BETWEENDOUBLEPATTERN, ComparatorType.BETWEEN);
		matcherComparatorTypes.put(patternType.INLONGPATTERN,  ComparatorType.IN);
		matcherComparatorTypes.put(patternType.NOTINLONGPATTERN,  ComparatorType.NOT_IN);
		matcherComparatorTypes.put(patternType.INDOUBLEPATTERN,  ComparatorType.IN);
		matcherComparatorTypes.put(patternType.NOTINDOUBLEPATTERN,  ComparatorType.NOT_IN);
		matcherComparatorTypes.put(patternType.NOTEQUALSSTRINGPATTERN, ComparatorType.NOT_EQUALS);
		matcherComparatorTypes.put(patternType.INSTRINGPATTERN, ComparatorType.IN);
		matcherComparatorTypes.put(patternType.NOTINSTRINGPATTERN, ComparatorType.NOT_IN);
		matcherComparatorTypes.put(patternType.EQUALSSTRINGPATTERN, ComparatorType.EQUALS);
		matcherComparatorTypes.put(patternType.NOTLIKESTRINGPATTERN, ComparatorType.NOT_LIKE);
		matcherComparatorTypes.put(patternType.LIKESTRINGPATTERN, ComparatorType.LIKE);
		matcherComparatorTypes.put(patternType.EQUALSBOOLEANPATTERN, ComparatorType.EQUALS);

	}

	public static  Matcher getMatcher(patternType keyword) {
		return matchers.get(keyword).getMatcher();
	}

	public static DataType getDataType(patternType keyword) {
		return matcherDataTypes.get(keyword);
	}

	public static ComparatorType getComparator(patternType keyword) {
		return matcherComparatorTypes.get(keyword);
	}

	public static DecisionTableCondition getCondition(patternType keyword) {
		return matchers.get(keyword).getCondition();
	}

}
