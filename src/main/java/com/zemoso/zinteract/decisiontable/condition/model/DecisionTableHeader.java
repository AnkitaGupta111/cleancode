package com.zemoso.zinteract.decisiontable.condition.model;

import com.zemoso.zinteract.util.DataType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Praveen on 05-Jan-15.
 */
public class DecisionTableHeader {

    private Map<String, DataType> conditions = new HashMap<>();

    public Map<String, DataType> getConditions() {
        return this.conditions;
    }

    public void addConditions(String s, DataType e) {
        this.conditions.put(s, e);
    }

}
