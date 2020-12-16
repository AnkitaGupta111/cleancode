package com.zemoso.zinteract.decisiontable;

import java.util.List;

/**
 * Created by Praveen on 19-Dec-14.
 */
public class InCondition extends GenericCondition {
    private StringConstants comparatorName;
    private List<GenericCondition> inCondition;
    private String conditionName;
    private StringConstants dataType;

    public void setConditionValue(List<GenericCondition> l) {
        this.inCondition = l;
    }

    public StringConstants getDataType() {
        return this.dataType;
    }

    public List<GenericCondition> getInCondition() {
        return this.inCondition;
    }

    public void setInCondition(List<GenericCondition> g) {
        this.inCondition = g;
    }

    public StringConstants getComparatorName() {
        return comparatorName;
    }

    public void setComparatorName(StringConstants s) {
        this.comparatorName = s;
    }

    public String getConditionName() {
        return conditionName;
    }
}