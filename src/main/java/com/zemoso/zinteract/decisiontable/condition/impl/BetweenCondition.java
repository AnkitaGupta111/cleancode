package com.zemoso.zinteract.decisiontable.condition.impl;


import com.zemoso.zinteract.util.StringConstants;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;

public class BetweenCondition extends GenericCondition {
    private StringConstants comparatorName;
    private GenericCondition lessThanCondition;
    private GenericCondition greaterThanCondition;
    private String conditionName;
    private StringConstants dataType;

    public void setConditionValue(GenericCondition l,GenericCondition g) {
        this.lessThanCondition = l;
        this.greaterThanCondition = g;
    }

    public StringConstants getDataType() {
        return this.dataType;
    }

    public void setDataType(StringConstants s){
        this.dataType = s;
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

    public void setComparatorName(StringConstants s){
        this.comparatorName = s;
    }

    public StringConstants getComparatorName() {
        return comparatorName;
    }

    public String getConditionName() {
        return conditionName;
    }

}