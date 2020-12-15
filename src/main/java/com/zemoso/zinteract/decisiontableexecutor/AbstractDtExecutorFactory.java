package com.zemoso.zinteract.decisiontableexecutor;


import com.zemoso.zinteract.constants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDtExecutorFactory {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDtExecutorFactory.class);
    private static final String DEFAULT_DT_EXECUTOR_FACTORY_CLASS_NAME = Constant.EXECUTOR_FACTORY_CLASS_NAME;
    private static AbstractDtExecutorFactory dtExecutorFactory = null;
    protected Map<String, Object> hM = new HashMap<>();

    protected AbstractDtExecutorFactory() {

    }

    public static AbstractDtExecutorFactory getDtExecutorFactory() {
        if (dtExecutorFactory != null) {
            return dtExecutorFactory;
        } else {
            synchronized (AbstractDtExecutorFactory.class) {
                if (dtExecutorFactory != null) {
                    return dtExecutorFactory;
                }

                try {
                    Class<?> c = Class.forName(DEFAULT_DT_EXECUTOR_FACTORY_CLASS_NAME);
                    dtExecutorFactory = (AbstractDtExecutorFactory) c.newInstance();
                    return dtExecutorFactory;
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return dtExecutorFactory;
    }

    public abstract AbstractDtExecutor getDtExecutor(String dT_id, String json);

}
