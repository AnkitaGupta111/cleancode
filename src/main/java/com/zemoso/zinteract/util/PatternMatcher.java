package com.zemoso.zinteract.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Praveen on 23-Dec-14.
 */
public class PatternMatcher {

    private static Map<Enum,Matcher> matchers = new HashMap<Enum, Matcher>();

    public enum type{
        GREATERTHANLONGPATTERN,
        LESSTHANLONGPATTERN,
        EQUALSLONGPATTERN,
        NOTEQUALSLONGPATTERN,
        LESSTHANEQUALSLONGPATTERN,
        GREATERTHANDOUBLEPATTERN,
        EQUALSDOUBLEPATTERN,
        NOTEQUALSDOUBLEPATTERN,
        GREATERTHANEQUALSDOUBLEPATTERN,
        LESSTHANDOUBLEPATTERN,
        LESSTHANEQUALSDOUBLEPATTERN,
        GREATERTHANEQUALSLONGPATTERN,
        BETWEENLONGPATTERN,
        BETWEENDOUBLEPATTERN,
        INLONGPATTERN,
        NOTINLONGPATTERN,
        INDOUBLEPATTERN,
        NOTINDOUBLEPATTERN,
        NOTEQUALSSTRINGPATTERN,
        INSTRINGPATTERN,
        NOTINSTRINGPATTERN,
        EQUALSSTRINGPATTERN,
        EQUALSBOOLEANPATTERN,
        NOTLIKESTRINGPATTERN,
        LIKESTRINGPATTERN
    }

    static {
        matchers.put(type.GREATERTHANLONGPATTERN, Pattern.compile("(^>)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.LESSTHANLONGPATTERN,Pattern.compile("(^<)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.EQUALSLONGPATTERN,Pattern.compile("(^=*)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.NOTEQUALSLONGPATTERN,Pattern.compile("(^!=+)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.LESSTHANEQUALSLONGPATTERN,Pattern.compile("(^<\\s*=+)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.GREATERTHANDOUBLEPATTERN,Pattern.compile("(^>)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.EQUALSDOUBLEPATTERN,Pattern.compile("(^=*)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.NOTEQUALSDOUBLEPATTERN,Pattern.compile("(^!=+)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.GREATERTHANEQUALSDOUBLEPATTERN,Pattern.compile("(^>\\s*=+)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.LESSTHANDOUBLEPATTERN,Pattern.compile("(^<)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.LESSTHANEQUALSDOUBLEPATTERN,Pattern.compile("(^<\\s*=+)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.GREATERTHANEQUALSLONGPATTERN,Pattern.compile("(^>\\s*=+)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.BETWEENLONGPATTERN,Pattern.compile("(^between)(\\s*)(\\d+)(\\s*)(and)(\\s*)(\\d+$)").matcher(""));
        matchers.put(type.BETWEENDOUBLEPATTERN,Pattern.compile("(^between)(\\s*)(\\d+\\.\\d+)(\\s*)(and)(\\s*)(\\d+\\.\\d+$)").matcher(""));
        matchers.put(type.INLONGPATTERN,Pattern.compile("(^in)(\\s*)(\\d+,\\d+){1,}").matcher(""));
        matchers.put(type.NOTINLONGPATTERN,Pattern.compile("(^not)(\\s*)(in)(\\s*)(\\d+,\\d+){1,}").matcher(""));
        matchers.put(type.INDOUBLEPATTERN,Pattern.compile("(^in)(\\s*)(\\d+\\.\\d+,\\d+\\.\\d+){1,}").matcher(""));
        matchers.put(type.NOTINDOUBLEPATTERN,Pattern.compile("(^not)(\\s*)(in)(\\s*)(\\d+\\.\\d+,\\d+\\.\\d+){1,}").matcher(""));
        matchers.put(type.NOTEQUALSSTRINGPATTERN,Pattern.compile("(^!=+)(\\s*)(.*)").matcher(""));
        matchers.put(type.INSTRINGPATTERN,Pattern.compile("(^in)(\\s*)(.*,.*){1,}").matcher(""));
        matchers.put(type.NOTINSTRINGPATTERN,Pattern.compile("(^not)(\\s*)(in)(\\s*)(.*,.*){1,}").matcher(""));
        matchers.put(type.EQUALSSTRINGPATTERN,Pattern.compile("(^=*)(\\s*)(.*)").matcher(""));
        matchers.put(type.NOTLIKESTRINGPATTERN,Pattern.compile("(^!~+)(\\s*)(.*)").matcher(""));
        matchers.put(type.LIKESTRINGPATTERN,Pattern.compile("(^~+)(\\s*)(.*)").matcher(""));
        matchers.put(type.EQUALSBOOLEANPATTERN,Pattern.compile("(^=*)(\\s*)(true|false)",Pattern.CASE_INSENSITIVE).matcher(""));
    }

    public Matcher getMatcher(type keyword) {
        return matchers.get(keyword);
    }
}
