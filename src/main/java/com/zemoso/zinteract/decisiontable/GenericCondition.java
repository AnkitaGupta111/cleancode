package com.zemoso.zinteract.decisiontable;


public class GenericCondition extends DtCondition {
    private StringConstants comparatorName;

    private String conditionName;

    private StringConstants dataType;
    private ConditionValue conditionValue;

    public ConditionValue getConditionValue() {
        return this.conditionValue;
    }

    public void setConditionValue(ConditionValue conditionValue) {
        this.conditionValue = conditionValue;
    }

    public StringConstants getDataType() {
        return dataType;
    }

    public void setDataType(StringConstants dataType) {
        this.dataType = dataType;
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

    public void setConditionName(String s) {
        this.conditionName = s;
    }

}