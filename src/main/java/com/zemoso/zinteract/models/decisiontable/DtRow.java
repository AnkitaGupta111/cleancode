package com.zemoso.zinteract.models.decisiontable;

import java.util.HashMap;
import java.util.Map;

public class DtRow{
	private Map<String,DtCondition> conditionValues;
	private Map<String,DtAction> actions;

	public Map<String, DtScript> getScripts() {
		return scripts;
	}

	public void setScripts(String a, DtScript script) {
		this.scripts.put(a,script);
	}

	private Map<String,DtScript> scripts;

	public DtRow() {
		this.conditionValues = new HashMap<String, DtCondition>();
		this.actions = new HashMap<String, DtAction>();
		this.scripts=new HashMap<String, DtScript>();
	}

	public Map<String,DtCondition> getConditionValues() {
		return conditionValues;
	}

	public void setConditionValues(String condition, DtCondition dtCondition) {
		this.conditionValues.put(condition,dtCondition);
	}

    public Map<String,DtAction> getActions(){
        return actions;
    }
	public void setActions(String a,DtAction s){
		this.actions.put(a,s);
	}
}