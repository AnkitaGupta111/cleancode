package com.zemoso.zinteract.decisiontable.condition.model;

import java.util.Map;
import groovy.util.Eval;

public class DecisionTableScript {

	private String script;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

	public DecisionTableScript(String script) {
		this.script = script;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Boolean solve(Map<String, String> variables) {

		for (String var : variables.keySet()) {
			String x = "<" + var + ">";
			if (script.contains(x)) {
				script = script.replaceAll(x, variables.get(var));
			}
		}
		boolean scriptConditionMatched;
		try {
			scriptConditionMatched = Boolean.parseBoolean(Eval.me(script).toString());
		}
		catch (groovy.lang.MissingPropertyException e) {
			return false;
		}
		return scriptConditionMatched;
	}

}