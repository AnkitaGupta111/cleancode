package com.zemoso.zinteract.decisiontable.condition.model;

import java.util.Map;
import groovy.lang.MissingPropertyException;
import groovy.util.Eval;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecisionTableScript {

	private String script;

	private String type;

	public DecisionTableScript(String script) {
		this.script = script;
	}

	public Boolean solve(Map<String, String> variables) {

		for (Map.Entry<String, String> var : variables.entrySet()) {
			String key = var.getKey();
			String value = var.getValue();
			String scriptValue = "<" + key + ">";
			if (script.contains(scriptValue)) {
				script = script.replaceAll(scriptValue, value);
			}
		}
		boolean val;
		try {
			val = Boolean.parseBoolean(Eval.me(script).toString());
		}
		catch (MissingPropertyException e) {
			return false;
		}
		return val;
	}

}