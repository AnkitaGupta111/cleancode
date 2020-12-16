package com.zemoso.zinteract.decisiontable.condition.model;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import java.util.HashMap;
import java.util.Map;

public class DecisionTableRow {
	private Map<String, DecisionTableCondition> conditionValues;
	private Map<String, DecisionTableAction> actions;

	public Map<String, DeccisionTableScript> getScripts() {
		return scripts;
	}

	public void setScripts(String a, DeccisionTableScript script) {
		this.scripts.put(a,script);
	}

	private Map<String, DeccisionTableScript> scripts;

	public DecisionTableRow() {
		this.conditionValues = new HashMap<String, DecisionTableCondition>();
		this.actions = new HashMap<String, DecisionTableAction>();
		this.scripts=new HashMap<String, DeccisionTableScript>();
	}

	public Map<String, DecisionTableCondition> getConditionValues() {
		return conditionValues;
	}

	public void setConditionValues(String condition, DecisionTableCondition decisionTableCondition) {
		this.conditionValues.put(condition, decisionTableCondition);
	}

    public Map<String, DecisionTableAction> getActions(){
        return actions;
    }
	public void setActions(String a, DecisionTableAction s){
		this.actions.put(a,s);
	}
}