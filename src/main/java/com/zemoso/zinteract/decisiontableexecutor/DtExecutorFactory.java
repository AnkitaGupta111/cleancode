package com.zemoso.zinteract.decisiontableexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtExecutorFactory extends AbstractDtExecutorFactory {
    private static final Logger logger = LoggerFactory.getLogger(DtExecutorFactory.class);

    @Override
    public AbstractDtExecutor getDtExecutor(String dT_id, String json) {
        DtExecutor d = (DtExecutor) hM.get(dT_id);
        if (d == null) {
            logger.info("Executor does not exist ..lets create it");
            synchronized (this) {
                d = (DtExecutor) hM.get(dT_id);
                if (d == null)
                    d = new DtExecutor(json);
                hM.put(dT_id, d);
            }
            return d;
        }
        logger.info("Executor already exist...lets reuse it");
        return d;
    }
}