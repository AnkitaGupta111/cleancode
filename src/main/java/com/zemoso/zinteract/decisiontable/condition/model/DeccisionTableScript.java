package com.zemoso.zinteract.decisiontable.condition.model;

import groovy.util.Eval;

import java.util.Map;

public class DeccisionTableScript {

    private String script;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public DeccisionTableScript(String script){
        this.script=script;
    }
    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Boolean solve( Map<String, String> variables) {

        for ( String var : variables.keySet() ) {
            String x="<"+var+">";
            if(script.contains(x)){
                script = script.replaceAll(x, variables.get(var));
            }
        }
        boolean val;
        try {
            val=  Boolean.parseBoolean(Eval.me(script).toString());
        } catch (groovy.lang.MissingPropertyException e){
            return false;
        }
        return val;
    }


}