package com.zemoso.zinteract.decisiontableexecutor;

public class FactoryProducer{

	public static AbstractDtExecutorFactory getFactory() {
		if(true) {
			return DtExecutorFactory.getInstance();
		}
		else {
			//return DtExecutorFactory2.getInstance();
			return null;
		}

	}
}
