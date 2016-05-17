package com.zemoso.zinteract.decisiontable;

import groovy.util.Eval;

import java.util.HashMap;

public class DtAction{
	private String action;

    public void setVariables(HashMap<String, String> variables) {
        this.variables = variables;
    }

    private HashMap<String,String> variables;

    public boolean isScripted() {
        return isScripted;
    }

    public void setScripted(boolean scripted) {
        isScripted = scripted;
    }

    private boolean isScripted=false;

    public String getAction() {
        if(isScripted()==false){
            return action;
        } else {
            String actionString=action;
            for ( String var : variables.keySet() ){
                if(actionString.contains(var)){
                    actionString=actionString.replaceAll(var,variables.get(var));
                }
            }
            actionString= Eval.me(actionString).toString();
            return actionString;
        }
    }

    public void setAction(String s){
        this.action = s;
    }

    @Override
    public String toString(){
        if(isScripted()==false){
            return action;
        } else {
            String actionString=action;
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