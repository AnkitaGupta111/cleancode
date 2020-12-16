package com.zemoso.zinteract.decisiontable.condition.model;

/**
 * Created by Praveen on 23-Dec-14.
 */
public class ConditionValue {
    private String sConditionValue;
    private long lConditionValue;
    private double dConditionValue;
    private boolean bConditionValue;

    public ConditionValue(long l){
        this.lConditionValue = l;
    }

    public ConditionValue(double d){
        this.dConditionValue =d;
    }

    public ConditionValue(String s){
        this.sConditionValue = s;
    }

    public ConditionValue(boolean b){
        this.bConditionValue = b;
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

    public boolean getBooleanConditionValue(){
        return this.bConditionValue;
    }

    public void setBooleanConditionValue(boolean b){
        this.bConditionValue = b;
    }
}
