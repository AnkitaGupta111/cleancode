package com.zemoso.zinteract.decisiontableexecutor.abstractfactory;


import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDecisionTableExecutorFactory {
	private static AbstractDecisionTableExecutorFactory dtExecutorFactory = null;
	private static final String PROPERTY_KEY_FOR_DTEXECUTORFACTORY_CLASSNAME = "dtexecutorfactory_class_name";
	private static final String DEFAULT_DTEXECUTORFACTORY_CLASSNAME = "com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.DecisionTableExecutorFactory";

	protected Map<String,Object> hM = new HashMap<>();

	protected AbstractDecisionTableExecutorFactory() {

	}

	public abstract AbstractDecisionTableExecutor getDecisionTableExecutor(String dT_id, String rules);

	public static AbstractDecisionTableExecutorFactory getDtExecutorFactory() {
		if(dtExecutorFactory != null){
			return dtExecutorFactory;
		}
		else {
			synchronized (AbstractDecisionTableExecutorFactory.class){
				if(dtExecutorFactory != null){
					return dtExecutorFactory;
				}

				String className = System.getProperty(PROPERTY_KEY_FOR_DTEXECUTORFACTORY_CLASSNAME);
				if(className == null){
					className = DEFAULT_DTEXECUTORFACTORY_CLASSNAME;
				}
				try {
					Class<?> c = Class.forName(className);
					dtExecutorFactory = (AbstractDecisionTableExecutorFactory)c.newInstance();
					return dtExecutorFactory;
				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
		return dtExecutorFactory;
	}

}
