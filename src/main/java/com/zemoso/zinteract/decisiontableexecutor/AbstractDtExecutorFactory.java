package com.zemoso.zinteract.decisiontableexecutor;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDtExecutorFactory {

	private static AbstractDtExecutorFactory dtExecutorFactory = null;

	private static final String PROPERTY_KEY_FOR_DTEXECUTORFACTORY_CLASSNAME = "dtexecutorfactory_class_name";

	private static final String DEFAULT_DTEXECUTORFACTORY_CLASSNAME = "com.zemoso.zinteract.decisiontableexecutor.DtExecutorFactory";

	protected Map<String, Object> hM = new HashMap();

	protected AbstractDtExecutorFactory() {

	}

	public abstract AbstractDtExecutor getDtExecutor(String dT_id, String json);

	public static AbstractDtExecutorFactory getDtExecutorFactory() {
		if (dtExecutorFactory != null) {
			return dtExecutorFactory;
		}
		else {
			synchronized (AbstractDtExecutorFactory.class) {
				if (dtExecutorFactory != null) {
					return dtExecutorFactory;
				}

				String className = System.getProperty(PROPERTY_KEY_FOR_DTEXECUTORFACTORY_CLASSNAME);
				if (className == null) {
					className = DEFAULT_DTEXECUTORFACTORY_CLASSNAME;
				}
				try {
					Class c = Class.forName(className);
					dtExecutorFactory = (AbstractDtExecutorFactory) c.newInstance();
					return dtExecutorFactory;
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return dtExecutorFactory;
	}

}
