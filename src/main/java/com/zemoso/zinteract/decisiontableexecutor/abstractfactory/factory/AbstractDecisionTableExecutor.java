package com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;

import java.util.List;
import java.util.Map;

/**
 * Abstract class for DecisionTableExecutor
 */
public abstract class AbstractDecisionTableExecutor {

    /**
     * Returns the first match in the decision table, for the given value.
     * @param value to find in the decision table
     * @return @see DecisionTableResult
     */
    public abstract DecisionTableResult getFirstMatch(Map<String, String> value);

    /**
     * Returns the <i>List</i> of all matches in the decision table, for the given value.
     * @param value to find in the decision table
     * @return List<DecisionTableResult>
     */
    public abstract List<DecisionTableResult> getAllMatches(Map<String, String> value);

    /**
     * Returns the <i>List</i> of ActionResults, for the given value.
     * @param value to find in the decision table
     * @return List<Map<String, Map<String, String>>>
     */
    public abstract List<Map<String, Map<String, String>>> getAllActionResults(Map<String, String> value);

}
