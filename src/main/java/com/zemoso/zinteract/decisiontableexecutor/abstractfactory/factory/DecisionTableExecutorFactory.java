package com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory;

import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.impl.DecisionTableExecutor;

public class DecisionTableExecutorFactory extends AbstractDecisionTableExecutorFactory {

	@Override
	public AbstractDecisionTableExecutor getDecisionTableExecutor(String dT_id, String json) {

		DecisionTableExecutor d = (DecisionTableExecutor) hM.get(dT_id);
		if (d != null) {
			System.out.println("Executor already exist...lets reuse it");
			return d;
		}
		else {
			System.out.println("Executor does not exist ..lets create it");
			synchronized (this) {
				d = (DecisionTableExecutor) hM.get(dT_id);
				if (d != null) {
					return d;
				}
				else {
					d = new DecisionTableExecutor(new String[] { "" }, json);
					hM.put(dT_id, d);
					return d;
				}
			}
		}

	}

}