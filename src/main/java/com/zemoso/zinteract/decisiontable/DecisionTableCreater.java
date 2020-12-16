package com.zemoso.zinteract.decisiontable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableAction;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.BetweenCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTable;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableRow;
import com.zemoso.zinteract.decisiontable.condition.model.DeccisionTableScript;
import com.zemoso.zinteract.util.PatternMatcher;
import com.zemoso.zinteract.util.StringConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class DecisionTableCreater {
    private final String KEY_DT_NAME = "name";
    private final String KEY_DT_DESCRIPTION = "description";
    private final String KEY_DT_ARTIFACT_ID = "artifact_id";
    private final String KEY_DT_HEADER = "headers";
    private final String KEY_DT_OPTIONS = "options";
    private final String KEY_OPTION_IGNORECASE = "ignore_case";


    private String dTJson;
    public DecisionTableCreater(String s) {
        this.dTJson = s;
    }

    public DecisionTable createDtModel() {
        String dTJson = this.dTJson;
        //format and transform dT properly
        DecisionTable dT = new DecisionTable();
        JsonObject jObject = (JsonObject)new JsonParser().parse(dTJson);
        JsonArray options = jObject.getAsJsonArray(KEY_DT_OPTIONS);
        for(int o=0; o < options.size();o++){
            JsonObject option = options.get(o).getAsJsonObject();
            if(option.get("propname").getAsString().equals(KEY_OPTION_IGNORECASE)){
                Boolean ignoreCase = option.get("propvalue").getAsBoolean();
                dT.setIgnoreCase(ignoreCase);
            }
        }
        dT.setName(jObject.get(KEY_DT_NAME).toString());
        dT.setDescription(jObject.get(KEY_DT_DESCRIPTION).toString());
        dT.setArtifactId(jObject.get(KEY_DT_ARTIFACT_ID).toString());

        JsonObject headers = jObject.getAsJsonObject(KEY_DT_HEADER);
        JsonArray conditions = headers.getAsJsonArray("conditions");

        JsonArray actions = headers.getAsJsonArray("actions");

        JsonArray rows = jObject.getAsJsonArray("rows");
        DecisionTableRow row;
        DecisionTableCondition condition;
        DecisionTableAction decisionTableAction;
        ConditionValue conditionValue;
        List<DecisionTableRow> decisionTableRows = new ArrayList<DecisionTableRow>();
        for(int i=0;i < rows.size(); i++) {
            row = new DecisionTableRow();
            JsonArray conds = rows.get(i).getAsJsonObject().getAsJsonArray("conditions");
            JsonArray scripts = rows.get(i).getAsJsonObject().getAsJsonArray("scripts");
            for(int j=0; j < conds.size();j++) {
                String condValue = conds.get(j).getAsJsonObject().get("value").getAsString();
                String condName = conds.get(j).getAsJsonObject().get("condition").getAsString();
                condition = this.getCondition(condValue,"",condName);
                row.setConditionValues(condName,condition);
                if(i==0) {
                    dT.setHeaderConditions(condName, condition.getDataType());
                } else {
                    if(!dT.getHeader().getConditions().containsKey(condName)){
                        dT.setHeaderConditions(condName, condition.getDataType());
                    }
                }

            }
            if(scripts !=null) {
                for (int j = 0; j < scripts.size(); j++) {
                    String script = scripts.get(j).getAsJsonObject().get("script").getAsString();
                    String scriptName = scripts.get(j).getAsJsonObject().get("name").getAsString();
                    row.setScripts(scriptName, new DeccisionTableScript(script));
                }
            }
            JsonArray acs = rows.get(i).getAsJsonObject().getAsJsonArray("actions");
            for(int k=0;k < acs.size();k++){
                decisionTableAction = new DecisionTableAction();
                String acName = acs.get(k).getAsJsonObject().get("action").getAsString();
                for(JsonElement action: actions){
                    if(action.getAsJsonObject().get("action").getAsString().equals(acName)){
                        decisionTableAction.setType(action.getAsJsonObject().has("type")?action.getAsJsonObject().get("type").getAsString():"");
                    }
                }
                String acValue = acs.get(k).getAsJsonObject().get("value").getAsString();
                if(acs.get(k).getAsJsonObject().has("scripted") && acs.get(k).getAsJsonObject().get("scripted").getAsBoolean()){
                    decisionTableAction.setScripted(true);
                }
                decisionTableAction.setAction(acValue);
                row.setActions(acName, decisionTableAction);
               // String fg = "{\"name\":\" The table to decide\",\"description\":\" blah, blah ,,,,,....\",\"artifact_id\":235,\"options\":[{\"propname\":\"exit_on_first_match\",\"propvalue\":\"true\"}],\"headers\":{\"conditions\":[{\"condition\":\"purchase\"},{\"condition\":\"puchase_quantity\"}],\"actions\":[{\"action\":\"Segment\"}]},\"rows\":[{\"conditions\":[{\"value\":\">=1\"},{\"value\":\">=0\"}],\"actions\":[{\"value\":\"purchase_in_last_year\"}]},{\"conditions\":[{\"value\":\">=1\"},{\"value\":\">=30\"}],\"actions\":[{\"value\":\"big_purchaser\"}]}]}"
            }
            decisionTableRows.add(row);
        }
        dT.setDT(decisionTableRows);

        return dT;
    }

    private GenericCondition createGenericCondition(String condName, String cValue, StringConstants dataType, StringConstants comparatorName){
        ConditionValue conditionValue = new ConditionValue(cValue);
        GenericCondition genericCondition = new GenericCondition();
        genericCondition.setConditionName(condName);
        genericCondition.setComparatorName(comparatorName);
        genericCondition.setDataType(dataType);

        if(StringConstants.DATATYPE_LONG == dataType){
            conditionValue = new ConditionValue(Long.valueOf(cValue).longValue());

        }
        else if(StringConstants.DATATYPE_DOUBLE == dataType){
            conditionValue = new ConditionValue(Double.valueOf(cValue).doubleValue());
        }
        else if(StringConstants.DATATYPE_BOOLEAN == dataType){
            conditionValue = new ConditionValue(Boolean.valueOf(cValue).booleanValue());
        }
        else if(StringConstants.DATATYPE_STRING == dataType){
            conditionValue = new ConditionValue(cValue);
        }
        genericCondition.setConditionValue(conditionValue);

        return genericCondition;
    }

    private DecisionTableCondition getCondition(String conValue, String dataType, String condName) {
        //String equalsPattern = "=*\\s*(\\d+|\\d*.\\d+|\w+))";

        PatternMatcher patternMatcher = new PatternMatcher();
        GenericCondition finalCondition;
        Matcher m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANLONGPATTERN);
        m.reset(conValue);
        if (m.find()) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_GREATERTHAN);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_LESSTHAN);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTEQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_NOT_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANEQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_LESSTHAN_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_GREATERTHAN);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.NOTEQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_NOT_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANEQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_GREATERTHAN_EQUALS);
            }
        }


        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_LESSTHAN);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.LESSTHANEQUALSDOUBLEPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_LESSTHAN_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.GREATERTHANEQUALSLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_GREATERTHAN_EQUALS);
            }
        }


        m = patternMatcher.getMatcher(PatternMatcher.type.BETWEENLONGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(2);
            if(group1 != null && m.group(3) != null && m.group(5) != null && m.group(7) != null) {
                BetweenCondition bCondition = new BetweenCondition();
                GenericCondition lessT = createGenericCondition(condName,m.group(7),StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_LESSTHAN);

                GenericCondition greatT = createGenericCondition(condName,m.group(3),StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_GREATERTHAN);

                bCondition.setLessThanCondition(lessT);
                bCondition.setGreaterThanCondition(greatT);
                bCondition.setDataType(StringConstants.DATATYPE_LONG);
                bCondition.setConditionName(condName);
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
                GenericCondition lessT = createGenericCondition(condName,m.group(7),StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_LESSTHAN);

                GenericCondition greatT = createGenericCondition(condName,m.group(3),StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_GREATERTHAN);

                bCondition.setLessThanCondition(lessT);
                bCondition.setGreaterThanCondition(greatT);
                bCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
                bCondition.setConditionName(condName);
                bCondition.setComparatorName(StringConstants.COMPARATOR_BETWEEN);
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

                String[] longs = m.group(3).split(",");
                List<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = createGenericCondition(condName,s,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_EQUALS);
                    list.add(finalCondition);
                }

                inCondition.setComparatorName(StringConstants.COMPARATOR_IN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_LONG);
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

                String[] longs = m.group(5).split(",");
                List<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = createGenericCondition(condName,s,StringConstants.DATATYPE_LONG,StringConstants.COMPARATOR_NOT_EQUALS);
                    list.add(finalCondition);
                }
                inCondition.setComparatorName(StringConstants.COMPARATOR_NOTIN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_LONG);
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

                String[] longs = m.group(3).split(",");
                List<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = createGenericCondition(condName,s,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_EQUALS);
                    list.add(finalCondition);
                }
                inCondition.setComparatorName(StringConstants.COMPARATOR_IN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
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

                String[] longs = m.group(5).split(",");
                List<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = createGenericCondition(condName,s,StringConstants.DATATYPE_DOUBLE,StringConstants.COMPARATOR_NOT_EQUALS);
                    list.add(finalCondition);
                }
                inCondition.setComparatorName(StringConstants.COMPARATOR_NOTIN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_DOUBLE);
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
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_STRING,StringConstants.COMPARATOR_NOT_EQUALS);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.INSTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                InCondition inCondition = new InCondition();

                String[] longs = m.group(3).split(",");
                List<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = createGenericCondition(condName,s,StringConstants.DATATYPE_STRING,StringConstants.COMPARATOR_EQUALS);
                    list.add(finalCondition);
                }
                inCondition.setComparatorName(StringConstants.COMPARATOR_IN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_STRING);
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

                String[] longs = m.group(5).split(",");
                List<GenericCondition> list = new ArrayList<GenericCondition>();
                for(String s : longs){
                    finalCondition = createGenericCondition(condName,s,StringConstants.DATATYPE_STRING,StringConstants.COMPARATOR_NOT_EQUALS);
                    list.add(finalCondition);
                }
                inCondition.setComparatorName(StringConstants.COMPARATOR_NOTIN);
                inCondition.setConditionName(condName);
                inCondition.setDataType(StringConstants.DATATYPE_STRING);
                inCondition.setConditionValue(list);
                return inCondition;
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSBOOLEANPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_BOOLEAN,StringConstants.COMPARATOR_EQUALS);
            }
        }

        //TODO add Not equals boolean condition
        m = patternMatcher.getMatcher(PatternMatcher.type.NOTLIKESTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_STRING,StringConstants.COMPARATOR_NOT_LIKE);
            }
        }
        m = patternMatcher.getMatcher(PatternMatcher.type.LIKESTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_STRING,StringConstants.COMPARATOR_LIKE);
            }
        }

        m = patternMatcher.getMatcher(PatternMatcher.type.EQUALSSTRINGPATTERN);
        m.reset(conValue);
        if (m.find( )) {
            String group1 = m.group(1);
            String group2 = m.group(3);
            if(group1 != null && group2 != null) {
                return createGenericCondition(condName,group2,StringConstants.DATATYPE_STRING,StringConstants.COMPARATOR_EQUALS);
            }
        }

        return null;

    }
}
