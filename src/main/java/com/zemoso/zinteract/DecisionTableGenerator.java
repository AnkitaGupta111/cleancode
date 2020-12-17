package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class DecisionTableGenerator {

	private static Logger LOGGER = Logger.getLogger(DecisionTableGenerator.class);

	public static DecisionTableResult execute(HashMap<String, String> s, String json) {
		AbstractDecisionTableExecutorFactory Factory = AbstractDecisionTableExecutorFactory.getDtExecutorFactory();
		AbstractDecisionTableExecutor decisionTableExecutor = Factory.getDecisionTableExecutor("dT_id1", json);
		DecisionTableResult result = decisionTableExecutor.getFirstMatch(s);
		System.out.println(result.getActionResults());
		return result;
	}

}
