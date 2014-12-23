package com.zemoso.zinteract.decisiontable;


public class GenericCondition extends DtCondition{
    private StringConstants comparatorName;
    private String sConditionValue;
    private long lConditionValue;
    private double dConditionValue;
    private String conditionName;
    private StringConstants dataType;

    public void setConditionValue(String s) {
        this.sConditionValue = s;
    }

    public void setConditionValue(long s) {
        this.lConditionValue = s;
    }

    public void setConditionValue(double s) {
        this.dConditionValue = s;
    }

    public String getConditionValue(String s) {
        return this.sConditionValue;
    }

    public long getConditionValue(long d) {
        return this.lConditionValue;
    }

    public double getConditionValue(double d) {
        return this.dConditionValue;
    }

    public StringConstants getComparatorName() {
        return comparatorName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public StringConstants getDataType() {
        return this.dataType;
    }

    public void setDataType(StringConstants s) {
        this.dataType = s;
    }

    public void setComparatorName(StringConstants s) {
        this.comparatorName = s;
    }

    public void setConditionName(String s) {
        this.conditionName = s;
    }

}