package com.zemoso.zinteract.decisiontableexecutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DtExecutorFactory extends AbstractDtExecutorFactory {

    @Override
    public AbstractDtExecutor getDtExecutor(String dT_id, String json) {


        DtExecutor d = (DtExecutor) hM.get(dT_id);
        if (d != null) {
            log.info("Executor already exist...lets reuse it");
            return d;
        } else {
            log.info("Executor does not exist ..lets create it");
            synchronized (this) {
                d = (DtExecutor) hM.get(dT_id);
                if (d != null) {
                    return d;
                } else {
                    d = new DtExecutor(new String[]{""}, json);
                    hM.put(dT_id, d);
                    return d;
                }
            }
        }
    }
}