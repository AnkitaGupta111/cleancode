package com.zemoso.zinteract.decisiontable.condition;


import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.util.StringConstants;

public class GenericCondition extends DecisionTableCondition {
    private StringConstants comparatorName;

    private String conditionName;

    private StringConstants dataType;
    private ConditionValue conditionValue;

    public ConditionValue getConditionValue(){
        return this.conditionValue;
    }
    
    public StringConstants getDataType() {
        return dataType;
    }

    public void setDataType(StringConstants dataType) {
        this.dataType = dataType;
    }

    public void setConditionValue(ConditionValue conditionValue) {
        this.conditionValue = conditionValue;
    }

    public StringConstants getComparatorName() {
        return comparatorName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setComparatorName(StringConstants s) {
        this.comparatorName = s;
    }

    public void setConditionName(String s) {
        this.conditionName = s;
    }

}