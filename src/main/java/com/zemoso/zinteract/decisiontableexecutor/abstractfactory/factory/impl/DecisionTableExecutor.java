package com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.DecisionTableCreater;
import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTable;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableRow;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableScript;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import com.zemoso.zinteract.util.StringConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DecisionTableExecutor, will give the Actions results based on the rules configured and the values passed.
 */
public class DecisionTableExecutor extends AbstractDecisionTableExecutor {

    private final String rules;
    private DecisionTable decisionTable = null;

    public DecisionTableExecutor(String rules) {
        this.rules = rules;
    }

    public DecisionTable getDecisionTable() {
        if (decisionTable == null) {
            decisionTable = createDT();
        }
        return decisionTable;
    }

    /**
     * To get all the Matched Action Results, based on the given value.
     * @param value to find in the decision table
     * @return <i>List</i> of Action Results
     */
    public List<Map<String, Map<String, String>>> getAllActionResults(Map<String, String> value) {
        return getAllMatches(value).stream()
                .map(DecisionTableResult::getActionResults)
                .collect(Collectors.toList());
    }

    /**
     * To get the FirstMatch from the Action Results, based on the given value.
     * @param value to find in the decision table
     * @return
     */
    public DecisionTableResult getFirstMatch(Map<String, String> value) {
        List<DecisionTableResult> allMatches = findMatches(value, true);
        return (allMatches.size() > 0) ? allMatches.get(0) : null;
    }

    /**
     * To get all the matched values based on the given value.
     * @param value to find in the decision table
     * @return
     */
    public List<DecisionTableResult> getAllMatches(Map<String, String> value) {
        return findMatches(value, false);
    }

    private List<DecisionTableResult> findMatches(Map<String, String> valueMap, Boolean firstOnly) {
        boolean match;
        List<DecisionTableResult> results = new ArrayList<>();
        for (DecisionTableRow decisionTableRow : getDecisionTable().getDt()) {
            match = compareValueWithRules(decisionTableRow, valueMap);
            if (match && decisionTableRow.getScripts() != null) {
                match = solveTheScript(decisionTableRow, valueMap);
            }
            if (match) {
                results.add(createResult(valueMap, decisionTableRow));
                if (firstOnly) {
                    break;
                }
            }
        }
        return results;
    }

    private DecisionTableResult createResult(Map<String, String> valueMap, DecisionTableRow decisionTableRow) {
        DecisionTableResult result = new DecisionTableResult();
        result.setVariables(valueMap);
        result.setRow(decisionTableRow);
        return result;
    }

    private boolean solveTheScript(DecisionTableRow decisionTableRow, Map<String, String> valueMap) {
        for (Map.Entry<String, DecisionTableScript> decisionTableScriptsEntry : decisionTableRow.getScripts().entrySet()) {
            DecisionTableScript script = decisionTableScriptsEntry.getValue();
            if (!script.solve(valueMap)) {
                return false;
            }
        }
        return true;
    }

    private boolean compareValueWithRules(DecisionTableRow decisionTableRow, Map<String, String> valueMap) {
        AbstractComparatorFactory abstractComparatorFactory = AbstractComparatorFactory.getComparatorFactory();
        Comparator comparator;
        Boolean ignoreCase = getDecisionTable().getIgnoreCase();
        Map<String, Enum<?>> headersWithDatatype = getDecisionTable().getHeader().getConditions();
        DecisionTableCondition decisionTableCondition;
        boolean match = false;
        for (Map.Entry<String, Enum<?>> headerWithDatatypeEntry : headersWithDatatype.entrySet()) {
            decisionTableCondition = decisionTableRow.getConditionValues().get(headerWithDatatypeEntry.getKey());
            if (decisionTableCondition != null) {
                comparator = abstractComparatorFactory.getComparator(decisionTableCondition.getComparatorName());
                String value = valueMap.get(headerWithDatatypeEntry.getKey());
                Enum<?> dataType = headersWithDatatype.get(headerWithDatatypeEntry.getKey());
                match = comparator.satisfies(decisionTableCondition, getConditionValue(value, dataType), ignoreCase);
                if (!match) {
                    return match;
                }
            }
        }
        return match;
    }

    private DecisionTable createDT() {
        DecisionTableCreater decisionTableCreater = new DecisionTableCreater(rules);
        return decisionTableCreater.createDecisionTable();
    }

    private ConditionValue getConditionValue(String value, Enum<?> dataType) {
        ConditionValue cValue;
        if (dataType == null) {
            return null;
        }
        if (dataType.equals(StringConstants.DATATYPE_STRING)) {
            cValue = new ConditionValue(value);
        } else if (dataType.equals(StringConstants.DATATYPE_LONG)) {
            cValue = new ConditionValue(Long.parseLong(value));
        } else if (dataType.equals(StringConstants.DATATYPE_DOUBLE)) {
            cValue = new ConditionValue(Double.parseDouble(value));
        } else if (dataType.equals(StringConstants.DATATYPE_BOOLEAN)) {
            cValue = new ConditionValue(Boolean.parseBoolean(value));
        } else {
            cValue = new ConditionValue(value);
        }
        return cValue;
    }

}
