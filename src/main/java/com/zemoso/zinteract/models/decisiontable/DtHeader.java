package com.zemoso.zinteract.models.decisiontable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Praveen on 05-Jan-15.
 */
public class DtHeader {
    private Map<String,Enum> conditions = new HashMap<String, Enum>();

    public Map<String,Enum> getConditions(){
        return this.conditions;
    }

    public void addConditions(String s, Enum e){
        this.conditions.put(s,e);
    }
}
