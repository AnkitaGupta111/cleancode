package com.zemoso.zinteract.decisiontable;

import java.util.HashMap;

/**
 * Created by Praveen on 05-Jan-15.
 */
public class DtHeader {
    private HashMap<String,Enum> conditions = new HashMap<String, Enum>();

    public HashMap<String,Enum> getConditions(){
        return this.conditions;
    }

    public void addConditions(String s, Enum e){
        this.conditions.put(s,e);
    }
}
