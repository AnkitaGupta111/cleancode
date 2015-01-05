package com.zemoso.zinteract.decisiontable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class DtCreater {
    private String dTJson;
    public DtCreater(String s) {
        this.dTJson = s;
    }

    public DecisionTable createDtModel(String dTJson) {
        //format and transform dT properly
        DecisionTable dT = new DecisionTable();
        JsonObject jObject = (JsonObject)new JsonParser().parse(dTJson);
        dT.setName(jObject.get("name").toString());
        dT.setDescription(jObject.get("description").toString());
        dT.setArtifactId(jObject.get("artifact_id").toString());

        JsonObject headers = jObject.getAsJsonObject("headers");
        JsonArray conditions = headers.getAsJsonArray("conditions");

        JsonArray actions = headers.getAsJsonArray("actions");

        JsonArray rows = jObject.getAsJsonArray("rows");
        DtRow row;
        DtCondition condition;
        DtAction dtAction;
        ConditionValue conditionValue;
        ArrayList<DtRow> dtRows = new ArrayList<DtRow>();
        for(int i=0;i < rows.size(); i++) {
            row = new DtRow();
            JsonArray conds = rows.get(i).getAsJsonObject().getAsJsonArray("conditions");
            for(int j=0; j < conditions.size();j++) {
                String condValue = conds.get(j).getAsJsonObject().get("value").getAsString();
                String condName = conditions.get(j).getAsJsonObject().get("condition").getAsString();
                condition = this.getCondition(condValue,"",condName);
                row.setConditionValues(condName,condition);
                if(i==0) {
                    dT.setHeaderConditions(condName, condition.getDataType());
                }

            }
            JsonArray acs = rows.get(i).getAsJsonObject().getAsJsonArray("actions");
            for(int k=0;k < actions.size();k++){
                dtAction = new DtAction();
                String acName = actions.get(k).getAsJsonObject().get("action").getAsString();
                dtAction.setAction(acName);
                row.setActions(acName,dtAction);
            }
            dtRows.add(row);
        }
        dT.setDT(dtRows);

        return dT;
    }

    private DtCondition getCondition(String conValue,String dataType, String condName) {
        //String equalsPattern = "=*\\s*(\\d+|\\d*.\\d+|\w+))";

        PatternMatcher patternMatcher = new PatternMatcher();
        GenericCondition finalCondition;
        Matcher m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                finalCondition.setConditionValue(new ConditionValue(Long.valueOf(group2).longValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_GREATERTHAN);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                finalCondition.setConditionValue(new ConditionValue(Long.valueOf(group2).longValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_LESSTHAN);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                finalCondition.setConditionValue(new ConditionValue(Long.valueOf(group2).longValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTEQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                finalCondition.setConditionValue(new ConditionValue(Long.valueOf(group2).longValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_NOT_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANEQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                finalCondition.setConditionValue(new ConditionValue(Long.valueOf(group2).longValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_LESSTHAN_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                finalCondition.setConditionValue(new ConditionValue(Double.valueOf(group2).doubleValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_GREATERTHAN);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                finalCondition.setConditionValue(new ConditionValue(Double.valueOf(group2).doubleValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTEQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                finalCondition.setConditionValue(new ConditionValue(Double.valueOf(group2).doubleValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_NOT_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANEQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                finalCondition.setConditionValue(new ConditionValue(Double.valueOf(group2).doubleValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_GREATERTHAN_EQUALS);
                return finalCondition;
            }
        }


        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                finalCondition.setConditionValue(new ConditionValue(Double.valueOf(group2).doubleValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_LESSTHAN);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANEQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                finalCondition.setConditionValue(new ConditionValue(Double.valueOf(group2).doubleValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_LESSTHAN_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANEQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                finalCondition.setConditionValue(new ConditionValue(Long.valueOf(group2).longValue()));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_GREATERTHAN_EQUALS);
                return finalCondition;
            }
        }


        m = patternMatcher.getMatcher(PatternMatcher.type.BETWEENLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(2);
            if(group1 != null && m.group(3) != null && m.group(5) != null && m.group(7) != null) {
                BetweenCondition bCondition = new BetweenCondition();
                GenericCondition lessT = new GenericCondition();
                lessT.setDataType(StringConstants.DATATYPE_LONG);
                lessT.setComparatorName(StringConstants.COMPARATOR_LESSTHAN);
                lessT.setConditionName(condName);
                lessT.setConditionValue(new ConditionValue(Long.valueOf(m.group(7)).longValue()));
                bCondition.setLessThanCondition(lessT);
                bCondition.setDataType(StringConstants.DATATYPE_LONG);

                GenericCondition greatT = new GenericCondition();
                greatT.setConditionName(condName);
                greatT.setComparatorName(StringConstants.COMPARATOR_GREATERTHAN);
                greatT.setDataType(StringConstants.DATATYPE_LONG);
                greatT.setConditionValue(new ConditionValue(Long.valueOf(m.group(3)).longValue()));

                bCondition.setGreaterThanCondition(greatT);
                bCondition.setComparatorName(StringConstants.COMPARATOR_BETWEEN);
                return bCondition;
            }
        }


        m = patternMatcher.getMatcher(PatternMatcher.type.BETWEENDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(2);
            if(group1 != null && m.group(3) != null && m.group(5) != null && m.group(7) != null) {
                BetweenCondition bCondition = new BetweenCondition();
                GenericCondition lessT = new GenericCondition();
                lessT.setDataType(StringConstants.DATATYPE_DOUBLE);
                lessT.setComparatorName(StringConstants.COMPARATOR_LESSTHAN);
                lessT.setConditionName(condName);
                lessT.setConditionValue(new ConditionValue(Double.valueOf(m.group(7)).doubleValue()));
                bCondition.setLessThanCondition(lessT);
                bCondition.setDataType(StringConstants.DATATYPE_DOUBLE);

                GenericCondition greatT = new GenericCondition();
                greatT.setConditionName(condName);
                greatT.setComparatorName(StringConstants.COMPARATOR_GREATERTHAN);
                greatT.setDataType(StringConstants.DATATYPE_DOUBLE);
                greatT.setConditionValue(new ConditionValue(Long.valueOf(m.group(3)).doubleValue()));

                bCondition.setGreaterThanCondition(greatT);
                return bCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.INLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.COMPARATOR_IN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_LONG);
                String[] longs = m.group(3).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.COMPARATOR_EQUALS);
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                    finalCondition.setConditionValue(new ConditionValue(Long.valueOf(s).longValue()));
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTINLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null && m.group(5) != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.COMPARATOR_NOTIN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_LONG);
                String[] longs = m.group(5).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.COMPARATOR_NOT_EQUALS);
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.DATATYPE_LONG);
                    finalCondition.setConditionValue(new ConditionValue(Long.valueOf(s).longValue()));
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.INDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.COMPARATOR_IN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                String[] longs = m.group(3).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.COMPARATOR_EQUALS);
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                    finalCondition.setConditionValue(new ConditionValue(Double.valueOf(s).doubleValue()));
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTINDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null && m.group(5) !=null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.COMPARATOR_NOTIN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                String[] longs = m.group(5).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.COMPARATOR_NOT_EQUALS);
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                    finalCondition.setConditionValue(new ConditionValue(Double.valueOf(s).doubleValue()));
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTEQUALSSTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_STRING);
                finalCondition.setConditionValue(new ConditionValue((group2)));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_NOT_EQUALS);
                return finalCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.INSTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.COMPARATOR_IN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_STRING);
                String[] longs = m.group(3).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.COMPARATOR_EQUALS);
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.DATATYPE_STRING);
                    finalCondition.setConditionValue(new ConditionValue(s));
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTINSTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null && m.group(5) != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.COMPARATOR_NOTIN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_STRING);
                String[] longs = m.group(5).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.COMPARATOR_NOT_EQUALS);
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.DATATYPE_STRING);
                    finalCondition.setConditionValue(new ConditionValue(s));
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSSTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.DATATYPE_STRING);

                finalCondition.setConditionValue(new ConditionValue(group2));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.COMPARATOR_EQUALS);
                return finalCondition;
            }
        }

        return null;

    }
}
