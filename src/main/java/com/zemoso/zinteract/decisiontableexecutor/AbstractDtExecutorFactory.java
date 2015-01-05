package com.zemoso.zinteract.decisiontableexecutor;


import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDtExecutorFactory {
	public abstract AbstractDtExecutor getDtExecutor(String dT_id, String json);

	private static AbstractDtExecutorFactory dtExecutorFactory = null;
	protected Map<String,Object> hM = new HashMap();

	protected AbstractDtExecutorFactory() {

	}

	public static AbstractDtExecutorFactory getDtExecutorFactory() {
		if(dtExecutorFactory != null){
			return dtExecutorFactory;
		}
		else {
			synchronized (AbstractDtExecutorFactory.class){
				if(dtExecutorFactory != null){
					return dtExecutorFactory;
				}
				System.setProperty("dtexecutorfactory_class_name","com.zemoso.zinteract.decisiontableexecutor.DtExecutorFactory");
				String className = System.getProperty("dtexecutorfactory_class_name");
				try {
					Class c = Class.forName(className);
					dtExecutorFactory = (AbstractDtExecutorFactory)c.newInstance();
					return dtExecutorFactory;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return new DtExecutorFactory();//Default
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return dtExecutorFactory;
	}

}
