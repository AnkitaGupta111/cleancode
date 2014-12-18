package com.zemoso.zinteract.decisiontable;


public class BetweenCondition extends GenericCondition{
    private String comparatorName = "BetweenComparator";
    private GenericCondition lessThanCondition;
    private GenericCondition greaterThanCondition;
    private String conditionName;
    private String dataType;

    public void setConditionValue(GenericCondition l,GenericCondition g) {
        this.lessThanCondition = l;
        this.greaterThanCondition = g;
    }

    public String getDataType() {
        return this.dataType;
    }

    public GenericCondition getLessThanConditionValue() {
        return lessThanCondition;
    }

    public GenericCondition getGreaterThanConditionValue() {
        return greaterThanCondition;
    }

    public void setLessThanCondition(GenericCondition l){
        this.lessThanCondition = l;
    }

    public void setGreaterThanCondition(GenericCondition g){
        this.greaterThanCondition = g;
    }

    public void setComparatorName(String s){
        this.comparatorName = s;
    }

    public String getComparatorName() {
        return comparatorName;
    }

    public String getConditionName() {
        return conditionName;
    }

}