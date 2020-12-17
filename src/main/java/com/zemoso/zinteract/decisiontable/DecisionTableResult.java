package com.zemoso.zinteract.decisiontable;

import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableAction;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableRow;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import groovy.util.Eval;

/**
 * Created by deepak on 23/5/16.
 */
public class DecisionTableResult {

	public DecisionTableRow getRow() {
		return row;
	}

	public void setRow(DecisionTableRow row) {
		this.row = row;
		setActionResults();
	}

	public Map<String, Map<String, String>> getActionResults() {
		return actionResults;
	}

	public void setActionResults() {
		this.actionResults = new HashMap<String, Map<String, String>>();
		Map<String, DecisionTableAction> actionsMap = this.row.getActions();
		Iterator<Map.Entry<String, DecisionTableAction>> it = actionsMap.entrySet().iterator();
		Map<String, String> actionResult;
		while (it.hasNext()) {
			Map.Entry<String, DecisionTableAction> pair = it.next();
			DecisionTableAction action = pair.getValue();
			String actionValue = getActionValue(action);
			variables.put(pair.getKey(), actionValue);
			String actionType = action.getType();
			actionResult = new HashMap<String, String>();
			actionResult.put("type", actionType);
			actionResult.put("value", actionValue);
			this.actionResults.put(pair.getKey(), actionResult);
		}

	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	private Map<String, String> variables;

	private DecisionTableRow row;

	private Map<String, Map<String, String>> actionResults;

	public String getActionValue(DecisionTableAction action) {
		if (action.isScripted() == false) {
			return action.getAction();
		}
		else {
			String actionString = action.getAction();
			for (String var : variables.keySet()) {
				String x = "<" + var + ">";
				if (actionString.contains(x)) {
					actionString = actionString.replaceAll(x, variables.get(var));
				}
			}
			actionString = Eval.me(actionString).toString();
			return actionString;
		}
	}

}
