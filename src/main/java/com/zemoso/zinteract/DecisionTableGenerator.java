package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *
 */
@Slf4j
public class DecisionTableGenerator {

    /**
     * @param valueMap
     * @param rules
     * @return
     */
    public static DecisionTableResult execute(Map<String, String> valueMap, String rules) {
        AbstractDecisionTableExecutorFactory Factory = AbstractDecisionTableExecutorFactory.getExecutorFactory();
        AbstractDecisionTableExecutor decisionTableExecutor = Factory.getDecisionTableExecutor("dT_id1", rules);
        DecisionTableResult result = decisionTableExecutor.getFirstMatch(valueMap);
        log.info("result.getActionResults() ->", result.getActionResults());
        return result;
    }

}
