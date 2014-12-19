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
                    dT.setConditions(condName);
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


        GenericCondition finalCondition;
        String greaterThanLongPattern = "(^>)(\\s*)(\\d+$)";
        Pattern r = Pattern.compile(greaterThanLongPattern);
        // Now create matcher object.
        Matcher m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                finalCondition.setConditionValue(Long.valueOf(group2).longValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_GREATERTHAN());
                return finalCondition;
            }
        }

        String lessThanLongPattern = "(^<)(\\s*)(\\d+$)";
        r = Pattern.compile(lessThanLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                finalCondition.setConditionValue(Long.valueOf(group2).longValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_LESSTHAN());
                return finalCondition;
            }
        }

        String equalsLongPattern = "(^=*)(\\s*)(\\d+$)";
        r = Pattern.compile(equalsLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                finalCondition.setConditionValue(Long.valueOf(group2).longValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_EQUALS());
                return finalCondition;
            }
        }

        String notEqualsLongPattern = "(^!=+)(\\s*)(\\d+$)";
        r = Pattern.compile(notEqualsLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                finalCondition.setConditionValue(Long.valueOf(group2).longValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_NOT_EQUALS());
                return finalCondition;
            }
        }

        String lessThanEqualsLongPattern = "(^<\\s*=+)(\\s*)(\\d+$)";
        r = Pattern.compile(lessThanEqualsLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                finalCondition.setConditionValue(Long.valueOf(group2).longValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_LESSTHAN_EQUALS());
                return finalCondition;
            }
        }

        String greaterThanDoublePattern = "(^>)(\\s*)(\\d+\\.\\d+$)";
        r = Pattern.compile(greaterThanDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                finalCondition.setConditionValue(Double.valueOf(group2).doubleValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_GREATERTHAN());
                return finalCondition;
            }
        }

        String equalsDoublePattern = "(^=*)(\\s*)(\\d+\\.\\d+$)";

        r = Pattern.compile(equalsDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                finalCondition.setConditionValue(Double.valueOf(group2).doubleValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_EQUALS());
                return finalCondition;
            }
        }

        String notEqualsDoublePattern = "(^!=+)(\\s*)(\\d+\\.\\d+$)";
        r = Pattern.compile(notEqualsDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                finalCondition.setConditionValue(Double.valueOf(group2).doubleValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_NOT_EQUALS());
                return finalCondition;
            }
        }

        String greaterThanEqualsDoublePattern = "(^>\\s*=+)(\\s*)(\\d+\\.\\d+$)";
        r = Pattern.compile(greaterThanEqualsDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                finalCondition.setConditionValue(Double.valueOf(group2).doubleValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_GREATERTHAN_EQUALS());
                return finalCondition;
            }
        }


        String lessThanDoublePattern = "(^<)(\\s*)(\\d+\\.\\d+$)";

        r = Pattern.compile(lessThanDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                finalCondition.setConditionValue(Double.valueOf(group2).doubleValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_LESSTHAN());
                return finalCondition;
            }
        }

        String lessThanEqualsDoublePattern = "(^<\\s*=+)(\\s*)(\\d+\\.\\d+$)";
        r = Pattern.compile(lessThanEqualsDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                finalCondition.setConditionValue(Double.valueOf(group2).doubleValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_LESSTHAN_EQUALS());
                return finalCondition;
            }
        }

        String greaterThanEqualsLongPattern = "(^>\\s*=+)(\\s*)(\\d+$)";
        r = Pattern.compile(greaterThanEqualsLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                finalCondition.setConditionValue(Long.valueOf(group2).longValue());
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_GREATERTHAN_EQUALS());
                return finalCondition;
            }
        }


        String betweenLongPattern = "(^between)(\\s*)(\\d+)(\\s*)(and)(\\s*)(\\d+$)";
        r = Pattern.compile(betweenLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(2);
            if(group1 != null && m.group(3) != null && m.group(5) != null && m.group(7) != null) {
                BetweenCondition bCondition = new BetweenCondition();
                GenericCondition lessT = new GenericCondition();
                lessT.setDataType(StringConstants.getDATATYPE_LONG());
                lessT.setComparatorName(StringConstants.getCOMPARATOR_LESSTHAN());
                lessT.setConditionName(condName);
                lessT.setConditionValue(Long.valueOf(m.group(7)).longValue());
                bCondition.setLessThanCondition(lessT);

                GenericCondition greatT = new GenericCondition();
                greatT.setConditionName(condName);
                greatT.setComparatorName(StringConstants.getCOMPARATOR_GREATERTHAN());
                greatT.setDataType(StringConstants.getDATATYPE_LONG());
                greatT.setConditionValue(Long.valueOf(m.group(3)).longValue());

                bCondition.setGreaterThanCondition(greatT);
                bCondition.setComparatorName(StringConstants.getCOMPARATOR_BETWEEN());
                return bCondition;
            }
        }


        String betweenDoublePattern = "(^between)(\\s*)(\\d+\\.\\d+)(\\s*)(and)(\\s*)(\\d+\\.\\d+$)";
        r = Pattern.compile(betweenDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(2);
            if(group1 != null && m.group(3) != null && m.group(5) != null && m.group(7) != null) {
                BetweenCondition bCondition = new BetweenCondition();
                GenericCondition lessT = new GenericCondition();
                lessT.setDataType(StringConstants.getDATATYPE_DOUBLE());
                lessT.setComparatorName(StringConstants.getCOMPARATOR_LESSTHAN());
                lessT.setConditionName(condName);
                lessT.setConditionValue(Double.valueOf(m.group(7)).doubleValue());
                bCondition.setLessThanCondition(lessT);

                GenericCondition greatT = new GenericCondition();
                greatT.setConditionName(condName);
                greatT.setComparatorName(StringConstants.getCOMPARATOR_GREATERTHAN());
                greatT.setDataType(StringConstants.getDATATYPE_DOUBLE());
                greatT.setConditionValue(Long.valueOf(m.group(3)).doubleValue());

                bCondition.setGreaterThanCondition(greatT);
                return bCondition;
            }
        }

        String inLongPattern = "(^in)(\\s*)(\\d+,\\d+){1,}";
        r = Pattern.compile(inLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.getCOMPARATOR_IN());
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.getDATATYPE_LONG());
                String[] longs = m.group(3).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.getCOMPARATOR_EQUALS());
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                    finalCondition.setConditionValue(Long.valueOf(s).longValue());
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        String notInLongPattern = "(^not)(\\s*)(in)(\\s*)(\\d+,\\d+){1,}";
        r = Pattern.compile(notInLongPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null && m.group(5) != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.getCOMPARATOR_NOTIN());
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.getDATATYPE_LONG());
                String[] longs = m.group(5).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.getCOMPARATOR_NOT_EQUALS());
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.getDATATYPE_LONG());
                    finalCondition.setConditionValue(Long.valueOf(s).longValue());
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        String inDoublePattern = "(^in)(\\s*)(\\d+\\.\\d+,\\d+\\.\\d+){1,}";
        r = Pattern.compile(inDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.getCOMPARATOR_IN());
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                String[] longs = m.group(3).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.getCOMPARATOR_EQUALS());
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                    finalCondition.setConditionValue(Double.valueOf(s).doubleValue());
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        String notInDoublePattern = "(^not)(\\s*)(in)(\\s*)(\\d+\\.\\d+,\\d+\\.\\d+){1,}";
        r = Pattern.compile(notInDoublePattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null && m.group(5) !=null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.getCOMPARATOR_NOTIN());
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                String[] longs = m.group(5).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.getCOMPARATOR_NOT_EQUALS());
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.getDATATYPE_DOUBLE());
                    finalCondition.setConditionValue(Double.valueOf(s).doubleValue());
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        String notEqualsStringPattern = "(^!=+)(\\s*)(.*)";
        r = Pattern.compile(notEqualsStringPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_STRING());
                finalCondition.setConditionValue((group2));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_NOT_EQUALS());
                return finalCondition;
            }
        }

        String inStringPattern = "(^in)(\\s*)(.*,.*){1,}";
        r = Pattern.compile(inStringPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.getCOMPARATOR_IN());
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.getDATATYPE_STRING());
                String[] longs = m.group(3).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.getCOMPARATOR_EQUALS());
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.getDATATYPE_STRING());
                    finalCondition.setConditionValue(s);
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        String notInStringPattern = "(^not)(\\s*)(in)(\\s*)(.*,.*){1,}";
        r = Pattern.compile(notInStringPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null && m.group(5) != null) {
                InCondition inCondition = new InCondition();
                inCondition.setComparatorName(StringConstants.getCOMPARATOR_NOTIN());
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.getDATATYPE_STRING());
                String[] longs = m.group(5).split(",");
                ArrayList<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = new GenericCondition();
                    finalCondition.setComparatorName(StringConstants.getCOMPARATOR_NOT_EQUALS());
                    finalCondition.setConditionName(condName);
                    finalCondition.setDataType(StringConstants.getDATATYPE_STRING());
                    finalCondition.setConditionValue(s);
                    list.add(finalCondition);
                }
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        String equalsStringPattern = "(^=*)(\\s*)(.*)";
        r = Pattern.compile(equalsStringPattern);
        // Now create matcher object.
        m = r.matcher(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                finalCondition = new GenericCondition();
                finalCondition.setDataType(StringConstants.getDATATYPE_STRING());
                finalCondition.setConditionValue((group2));
                finalCondition.setConditionName(condName);
                finalCondition.setComparatorName(StringConstants.getCOMPARATOR_EQUALS());
                return finalCondition;
            }
        }

        return null;

    }
}
