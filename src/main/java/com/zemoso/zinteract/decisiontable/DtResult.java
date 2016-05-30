package com.zemoso.zinteract.decisiontable;

import groovy.util.Eval;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by deepak on 23/5/16.
 */
public class DtResult {

    public DtRow getRow() {
        return row;
    }

    public void setRow(DtRow row) {
        this.row = row;
        setActionResults();
    }

    public Map<String, Map<String, String>> getActionResults() {
        return actionResults;
    }

    public void setActionResults() {
        this.actionResults=new HashMap<String, Map<String, String>>();
        Map<String, DtAction> actionsMap=this.row.getActions();
        Iterator<Map.Entry<String, DtAction>> it = actionsMap.entrySet().iterator();
        Map<String, String> actionResult;
        while (it.hasNext()) {
            Map.Entry<String, DtAction> pair = it.next();
            DtAction action = pair.getValue();
            String actionValue=getActionValue(action);
            variables.put(pair.getKey(),actionValue);
            String actionType=action.getType();
            actionResult=new HashMap<String, String>();
            actionResult.put("type",actionType);
            actionResult.put("value",actionValue);
            this.actionResults.put(pair.getKey(),actionResult);
        }

    }
    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    private Map<String,String> variables;
    private DtRow row;
    private Map<String, Map<String, String>> actionResults;


    public String getActionValue(DtAction action) {
        if(action.isScripted()==false){
            return action.getAction();
        } else {
            String actionString=action.getAction();
            for ( String var : variables.keySet() ){
                if(actionString.contains(var)){
                    actionString=actionString.replaceAll(var,variables.get(var));
                }
            }
            actionString= Eval.me(actionString).toString();
            return actionString;
        }
    }




}
