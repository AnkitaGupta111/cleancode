package com.zemoso.zinteract.decisiontable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTable;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableAction;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableRow;
import com.zemoso.zinteract.decisiontable.condition.model.DecisionTableScript;
import com.zemoso.zinteract.util.ComparatorType;
import com.zemoso.zinteract.util.Constants;
import com.zemoso.zinteract.util.DataType;
import com.zemoso.zinteract.util.PatternMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * Created by Praveen on 18-Dec-14.
 */
public class DecisionTableCreator {

	private final String decisionTableInput;

	DecisionTable decisionTable;

	public DecisionTableCreator(String input) {
		this.decisionTableInput = input;
	}

	public DecisionTable createDecisionTable() {
		decisionTable = new DecisionTable();
		String decisionTableInput = this.decisionTableInput;
		JsonObject inputJson = (JsonObject) new JsonParser().parse(decisionTableInput);
		populateMetaInfo(inputJson);
		populateOptionValues(inputJson);
		populateDecisionTableRows(inputJson);
		return decisionTable;
	}

	/**
	 * This method extracts Meta info like name description from the input json and set
	 * the values in Decision Table.
	 * @param inputJson
	 */
	private void populateMetaInfo(JsonObject inputJson) {
		decisionTable.setName(inputJson.get(Constants.DECISION_TABLE_NAME).toString());
		decisionTable.setDescription(inputJson.get(Constants.DECISION_TABLE_DESCRIPTION).toString());
		decisionTable.setArtifactId(inputJson.get(Constants.DECISION_TABLE_ARTIFACT_ID).toString());
	}

	/**
	 * This method extracts options from the input json and set the values in Decision
	 * Table.
	 * @param inputJson
	 *
	 */
	private void populateOptionValues(JsonObject inputJson) {
		JsonArray options = inputJson.getAsJsonArray(Constants.DECISION_TABLE_OPTIONS);
		for (int o = 0; o < options.size(); o++) {
			JsonObject option = options.get(o).getAsJsonObject();
			if (option.get("propname").getAsString().equals(Constants.DECISION_TABLE_OPTION_IGNORE_CASE)) {
				Boolean ignoreCase = option.get("propvalue").getAsBoolean();
				decisionTable.setIgnoreCase(ignoreCase);
			}
		}
	}

	/**
	 * this method reads all the rows from input and create DecisionTableRow objects and
	 * set to decision table.
	 * @param inputJson
	 */
	private void populateDecisionTableRows(JsonObject inputJson) {
		JsonObject headers = inputJson.getAsJsonObject(Constants.DECISION_TABLE_HEADER);
		JsonArray actions = headers.getAsJsonArray("actions");
		JsonArray rows = inputJson.getAsJsonArray("rows");
		DecisionTableRow row;
		DecisionTableCondition condition;
		DecisionTableAction decisionTableAction;
		List<DecisionTableRow> decisionTableRows = new ArrayList<>();
		for (int i = 0; i < rows.size(); i++) {
			row = new DecisionTableRow();
			JsonArray conds = rows.get(i).getAsJsonObject().getAsJsonArray("conditions");
			JsonArray scripts = rows.get(i).getAsJsonObject().getAsJsonArray("scripts");
			for (int j = 0; j < conds.size(); j++) {
				String condValue = conds.get(j).getAsJsonObject().get("value").getAsString();
				String condName = conds.get(j).getAsJsonObject().get("condition").getAsString();
				condition = this.getCondition(condValue, condName).orElseThrow(() -> new RuntimeException(("Cannot assign condition for this value")));
				row.setConditionValues(condName, condition);
				if (i == 0) {
					decisionTable.setHeaderConditions(condName, condition.getDataType());
				}
				else {
					if (!decisionTable.getHeader().getConditions().containsKey(condName)) {
						decisionTable.setHeaderConditions(condName, condition.getDataType());
					}
				}

			}
			if (scripts != null) {
				for (int j = 0; j < scripts.size(); j++) {
					String script = scripts.get(j).getAsJsonObject().get("script").getAsString();
					String scriptName = scripts.get(j).getAsJsonObject().get("name").getAsString();
					row.setScripts(scriptName, new DecisionTableScript(script));
				}
			}
			JsonArray acs = rows.get(i).getAsJsonObject().getAsJsonArray("actions");
			for (int k = 0; k < acs.size(); k++) {
				decisionTableAction = new DecisionTableAction();
				String acName = acs.get(k).getAsJsonObject().get("action").getAsString();
				for (JsonElement action : actions) {
					if (action.getAsJsonObject().get("action").getAsString().equals(acName)) {
						decisionTableAction.setType(action.getAsJsonObject().has("type")
								? action.getAsJsonObject().get("type").getAsString() : "");
					}
				}
				String acValue = acs.get(k).getAsJsonObject().get("value").getAsString();
				if (acs.get(k).getAsJsonObject().has("scripted")
						&& acs.get(k).getAsJsonObject().get("scripted").getAsBoolean()) {
					decisionTableAction.setScripted(true);
				}
				decisionTableAction.setAction(acValue);
				row.setActions(acName, decisionTableAction);
			}
			decisionTableRows.add(row);
		}
		decisionTable.setDecisionTableRows(decisionTableRows);
	}

	private GenericCondition createGenericCondition(String condName, String value, DataType dataType,
			ComparatorType comparatorName) {
		ConditionValue conditionValue = new ConditionValue(value);
		GenericCondition genericCondition = new GenericCondition();
		genericCondition.setConditionName(condName);
		genericCondition.setComparatorName(comparatorName);
		genericCondition.setDataType(dataType);
		if (DataType.LONG == dataType) {
			conditionValue = new ConditionValue(Long.parseLong(value));
		}
		else if (DataType.DOUBLE == dataType) {
			conditionValue = new ConditionValue(Double.parseDouble(value));
		}
		else if (DataType.BOOLEAN == dataType) {
			conditionValue = new ConditionValue(Boolean.parseBoolean(value));
		}
		else if (DataType.STRING == dataType) {
			conditionValue = new ConditionValue(value);
		}
		genericCondition.setConditionValue(conditionValue);
		return genericCondition;
	}

	private Optional<DecisionTableCondition> getCondition(String conditionValue, String conditionName) {
		Optional<DecisionTableCondition> matchingCondition = Optional.empty();
		for (PatternMatcher.patternType type : PatternMatcher.matchers.keySet()) {
			Matcher matcher = PatternMatcher.getMatcher(type);
			matcher.reset(conditionValue);
			if (matcher.find()) {
				DecisionTableCondition condition = PatternMatcher.getCondition(type);
				matchingCondition = condition.getMatchingCondition(matcher, conditionName,
						conditionValue, type);
				if (matchingCondition.isPresent()) {
					return matchingCondition;
				}
			}
			}

		return matchingCondition;
	}

}
