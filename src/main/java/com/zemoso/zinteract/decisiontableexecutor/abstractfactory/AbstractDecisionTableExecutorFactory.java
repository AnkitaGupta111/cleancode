package com.zemoso.zinteract.decisiontableexecutor.abstractfactory;

import com.zemoso.zinteract.constants.Constants;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Factory Design Pattern is used to implement DecisionTableExecutorFactory.
 */
@Slf4j
public abstract class AbstractDecisionTableExecutorFactory {
    private static AbstractDecisionTableExecutorFactory executorFactory = null;
    protected Map<String, Object> mapOfIdAndExecutor = new HashMap<>();

    protected AbstractDecisionTableExecutorFactory() {
    }


    /**
     * Instantiates the DecisionTableExecutorFactory, if not exists.
     *
     * @return DecisionTableExecutorFactory instantiation
     */
    public static AbstractDecisionTableExecutorFactory getExecutorFactory() {
        if (executorFactory == null) {
            synchronized (AbstractDecisionTableExecutorFactory.class) {
                try {
                    Class<?> c = Class.forName(Constants.DEFAULT_DT_EXECUTOR_FACTORY_CLASS_NAME);
                    executorFactory = (AbstractDecisionTableExecutorFactory) c.newInstance();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    log.error("Error -->", e);
                }
            }
        }
        return executorFactory;
    }

    /**
     * This abstract method implementation will instantiates the DecisionTableExecutor,
     *
     * @param decisionTableId, Unique Id of the Decision Table
     * @param rules,           Rules of the Decision Table
     * @return AbstractDecisionTableExecutor
     */
    public abstract AbstractDecisionTableExecutor getDecisionTableExecutor(String decisionTableId, String rules);

}
