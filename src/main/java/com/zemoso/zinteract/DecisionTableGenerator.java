package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import java.util.Map;

public class DecisionTableGenerator {

	public static DecisionTableResult execute(Map<String, String> input, String json) {
		AbstractDecisionTableExecutorFactory factory = AbstractDecisionTableExecutorFactory.getDtExecutorFactory();
		AbstractDecisionTableExecutor decisionTableExecutor = factory.getDecisionTableExecutor("dT_id1", json);
		return decisionTableExecutor.getFirstMatch(input);
	}

}
