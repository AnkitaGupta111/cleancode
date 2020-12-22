package com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory;

import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.impl.DecisionTableExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * One of the Factory implementations of @see AbstractDecisionTableExecutorFactory, Which instantiate @see DecisionTableExecutor
 */
@Slf4j
public class DecisionTableExecutorFactory extends AbstractDecisionTableExecutorFactory {

    /**
     * Concrete method, returns the new instance for the @see DecisionTableExecutor, if not exists.
     * @param decisionTableId, Unique Id of the Decision Table
     * @param rules,           Rules of the Decision Table
     * @return AbstractDecisionTableExecutor
     */
    @Override
    public AbstractDecisionTableExecutor getDecisionTableExecutor(String decisionTableId, String rules) {
        DecisionTableExecutor decisionTableExecutor = (DecisionTableExecutor) mapOfIdAndExecutor.get(decisionTableId);
        if (decisionTableExecutor == null) {
            log.info("Executor does not exist ..lets create it");
            synchronized (this) {
                decisionTableExecutor = new DecisionTableExecutor(rules);
                mapOfIdAndExecutor.put(decisionTableId, decisionTableExecutor);
            }
            return decisionTableExecutor;
        }
        log.info("Executor already exist...lets reuse it");
        return decisionTableExecutor;
    }

}