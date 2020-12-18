package com.zemoso.zinteract.decisiontable;

import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableAction;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableRow;
import groovy.util.Eval;
import lombok.Data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by deepak on 23/5/16.
 */
@Data
public class DecisionTableResult {

    private Map<String, String> variables;

    private DecisionTableRow row;

    private Map<String, Map<String, String>> actionResults;

    public void setRow(DecisionTableRow row) {
        this.row = row;
        setActionResults();
    }

    public void setActionResults() {
        this.actionResults = new HashMap<>();
        Map<String, DecisionTableAction> actionsMap = this.row.getActions();
        Iterator<Map.Entry<String, DecisionTableAction>> actions = actionsMap.entrySet().iterator();
        Map<String, String> actionResult;
        while (actions.hasNext()) {
            Map.Entry<String, DecisionTableAction> pair = actions.next();
            DecisionTableAction action = pair.getValue();
            String actionValue = getActionValue(action);
            variables.put(pair.getKey(), actionValue);
            String actionType = action.getType();
            actionResult = new HashMap<>();
            actionResult.put("type", actionType);
            actionResult.put("value", actionValue);
            this.actionResults.put(pair.getKey(), actionResult);
        }
    }

    public String getActionValue(DecisionTableAction action) {
        if (!action.isScripted()) {
            return action.getAction();
        } else {
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
