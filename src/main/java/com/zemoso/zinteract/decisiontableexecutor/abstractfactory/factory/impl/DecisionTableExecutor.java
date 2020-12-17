package com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.abstractfactory.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.DecisionTableCreater;
import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTable;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableHeader;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableRow;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableScript;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import com.zemoso.zinteract.util.StringConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import groovy.util.Eval;

public class DecisionTableExecutor extends AbstractDecisionTableExecutor {

	private DecisionTable dT = null;

	private String[] options;

	private String dT_json;

	public DecisionTableExecutor(String[] options, String dt) {
		this.options = options;
		this.dT_json = dt;
	}

	public DecisionTable getDecisionTable() {
		if (dT != null) {
			return dT;
		}
		else {
			dT = createDT();
		}
		return dT;
	}

	public Map getFirstMatchActionResults(Map<String, String> value) {
		DecisionTableResult result = getFirstMatch(value);
		return result.getActionResults();
	}

	public List getAllActionResults(Map<String, String> value) {
		List actionResults = new ArrayList();
		List<DecisionTableResult> results = getAllMatches(value);
		for (DecisionTableResult result : results) {
			actionResults.add(result.getActionResults());
		}
		return actionResults;
	}

	public DecisionTableResult getFirstMatch(Map<String, String> value) {
		List<DecisionTableResult> allMatches = findMatches(value, true);
		if (allMatches.size() > 0) {
			return allMatches.get(0);
		}
		else
			return null;

	}

	public List<DecisionTableResult> getAllMatches(Map<String, String> value) {
		return findMatches(value, false);
	}

	private List<DecisionTableResult> findMatches(Map<String, String> valueMap, Boolean firstOnly) {
		AbstractComparatorFactory cFactory = AbstractComparatorFactory.getComparatorFactory();
		Iterator i, j;
		DecisionTableCondition decisionTableCondition;
		Comparator comparator;
		Boolean match = false;

		List<DecisionTableResult> results = new ArrayList<DecisionTableResult>();
		Map<String, ConditionValue> conditionValues = getConditionValues(valueMap);
		Map<String, Enum> headerConditions = getDecisionTable().getHeader().getConditions();
		Boolean ignoreCase = getDecisionTable().getIgnoreCase();
		Map<String, Enum> dtConditions = getDecisionTable().getHeader().getConditions();
		for (DecisionTableRow row : getDecisionTable().getDt()) {
			i = dtConditions.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				decisionTableCondition = row.getConditionValues().get(me.getKey().toString());
				if (decisionTableCondition == null) {
					continue;
				}
				comparator = cFactory.getComparator(decisionTableCondition.getComparatorName());
				ConditionValue cV;
				String value = valueMap.get(me.getKey().toString());
				Enum dataType = headerConditions.get(me.getKey().toString());
				cV = getConditionValue(value, dataType);
				match = comparator.satisfies(decisionTableCondition, cV, ignoreCase);
				if (!match) {
					break;
				}
			}
			if (match && row.getScripts() != null) {
				j = row.getScripts().entrySet().iterator();
				while (j.hasNext()) {
					Map.Entry me = (Map.Entry) j.next();
					DecisionTableScript script = (DecisionTableScript) me.getValue();
					if (!script.solve(valueMap)) {
						match = false;
						break;
					}
				}
			}
			if (match) {
				DecisionTableResult result = new DecisionTableResult();
				result.setVariables(valueMap);
				result.setRow(row);
				results.add(result);
			}
			if (match && firstOnly) {
				break;
			}
		}
		return results;
	}

	private ConditionValue solveScript(String key, Map<String, String> variables) {
		String keyCopy = key;
		for (String var : variables.keySet()) {
			if (keyCopy.contains(var)) {
				keyCopy = keyCopy.replaceAll(var, variables.get(var));
			}
		}
		String val = "";
		try {
			val = Eval.me(keyCopy).toString();
		}
		catch (groovy.lang.MissingPropertyException e) {
			return null;
		}
		Map<String, Enum> headerConditions = getDecisionTable().getHeader().getConditions();
		Enum dataType = headerConditions.get(key);
		return getConditionValue(val, dataType);
	}

	private DecisionTable createDT() {
		DecisionTableCreater decisionTableCreater = new DecisionTableCreater(dT_json);
		return decisionTableCreater.createDecisionTable();
	}

	private Map<String, ConditionValue> getConditionValues(Map<String, String> value) {
		Map<String, ConditionValue> conditionValues = new HashMap<String, ConditionValue>();
		DecisionTableHeader header = getDecisionTable().getHeader();
		Map<String, Enum> headerConditions = header.getConditions();
		Iterator i;
		ConditionValue cValue;
		i = value.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			Enum dataType = headerConditions.get(me.getKey());
			if (dataType == null) {
				continue;
			}
			cValue = getConditionValue(me.getValue().toString(), dataType);
			conditionValues.put(me.getKey().toString(), cValue);
		}
		return conditionValues;
	}

	private ConditionValue getConditionValue(String value, Enum dataType) {
		ConditionValue cValue;
		if (dataType == null) {
			return null;
		}
		if (dataType.equals(StringConstants.DATATYPE_STRING)) {
			cValue = new ConditionValue(value);
		}
		else if (dataType.equals(StringConstants.DATATYPE_LONG)) {
			cValue = new ConditionValue(Long.valueOf(value).longValue());
		}
		else if (dataType.equals(StringConstants.DATATYPE_DOUBLE)) {
			cValue = new ConditionValue(Double.valueOf(value).doubleValue());
		}
		else if (dataType.equals(StringConstants.DATATYPE_BOOLEAN)) {
			cValue = new ConditionValue(Boolean.valueOf(value).booleanValue());
		}
		else {
			cValue = new ConditionValue(value);
		}
		return cValue;
	}

}
