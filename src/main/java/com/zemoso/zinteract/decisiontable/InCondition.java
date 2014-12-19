package com.zemoso.zinteract.decisiontable;

import java.util.ArrayList;

/**
 * Created by Praveen on 19-Dec-14.
 */
public class InCondition extends GenericCondition{
    private String comparatorName = "InComparator";
    private ArrayList<GenericCondition> inCondition;
    private String conditionName;
    private String dataType;

    public void setConditionValue(ArrayList<GenericCondition> l) {
        this.inCondition = l;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setInCondition(ArrayList<GenericCondition> g){
        this.inCondition = g;
    }

    public ArrayList<GenericCondition> getInCondition(){
        return this.inCondition;
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
