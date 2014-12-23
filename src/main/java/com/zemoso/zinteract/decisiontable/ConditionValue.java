package com.zemoso.zinteract.decisiontable;

/**
 * Created by Praveen on 23-Dec-14.
 */
public class ConditionValue {
    private String sConditionValue;
    private long lConditionValue;
    private double dConditionValue;

    public ConditionValue(long l){
        this.lConditionValue = l;
    }

    public ConditionValue(double d){
        this.dConditionValue =d;
    }

    public ConditionValue(String s){
        this.sConditionValue = s;
    }

    public String getStringConditionValue() {
        return sConditionValue;
    }

    public void setStringConditionValue(String sConditionValue) {
        this.sConditionValue = sConditionValue;
    }

    public long getLongConditionValue() {
        return lConditionValue;
    }

    public void setLongConditionValue(long lConditionValue) {
        this.lConditionValue = lConditionValue;
    }

    public double getDoubleConditionValue() {
        return dConditionValue;
    }

    public void setDoubleConditionValue(double dConditionValue) {
        this.dConditionValue = dConditionValue;
    }
}
