package com.zemoso.zinteract.decisiontable;

import java.util.HashMap;

public class DtRow{
	private HashMap<String,DtCondition> conditionValues;
	private HashMap<String,DtAction> actions;

	public DtRow() {
		this.conditionValues = new HashMap<String, DtCondition>();
		this.actions = new HashMap<String, DtAction>();
	}

	public HashMap<String,DtCondition> getConditionValues() {
		return conditionValues;
	}

	public void setConditionValues(String condition, DtCondition dtCondition) {
		this.conditionValues.put(condition,dtCondition);
	}

    public HashMap<String,DtAction> getActions(){
        return actions;
    }
	public void setActions(String a,DtAction s){
		this.actions.put(a,s);
	}
}