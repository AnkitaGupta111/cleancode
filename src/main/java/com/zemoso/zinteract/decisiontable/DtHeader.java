package com.zemoso.zinteract.decisiontable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Praveen on 05-Jan-15.
 */
public class DtHeader {
    private final Map<String, Enum<?>> conditions = new HashMap<>();

    public Map<String, Enum<?>> getConditions() {
        return this.conditions;
    }

    public void addConditions(String s, Enum<?> e) {
        this.conditions.put(s, e);
    }
}
