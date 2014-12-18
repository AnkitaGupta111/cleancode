package com.zemoso.zinteract.decisiontable;


public class GenericCondition extends DtCondition{
    private String comparatorName = "";
    private String sConditionValue;
    private long lConditionValue;
    private double dConditionValue;
    private String conditionName;
    private String dataType;

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

    public String getComparatorName() {
        return comparatorName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String s) {
        this.dataType = s;
    }

    public void setComparatorName(String s) {
        this.comparatorName = s;
    }

    public void setConditionName(String s) {
        this.conditionName = s;
    }

}