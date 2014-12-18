package com.zemoso.zinteract.decisiontableexecutor;

import java.util.HashMap;
import java.util.Map;

public class DtExecutorFactory extends AbstractDtExecutorFactory{

	private static DtExecutorFactory dtExecutorFactory = new DtExecutorFactory();
	private Map<String,Object> hM = new HashMap();

	private DtExecutorFactory() {

	}

	public static DtExecutorFactory getInstance() {
		return dtExecutorFactory;
	}

	@Override
	public DtExecutor getDtExecutor(String dT_id, String json) {

		
			DtExecutor d = (DtExecutor) hM.get(dT_id);
			if(d != null) {
				System.out.println("Executor already exist...lets reuse it");
				return d;
			}
			else {
				System.out.println("Executor does not exist ..lets create it");
				synchronized(this){
					d = (DtExecutor) hM.get(dT_id);
					if(d != null) {
						return d;
					}
					else {
						d = new DtExecutor(new String[]{""},json);
						hM.put(dT_id,d);
						return d;
					}
				}				
			}
	
	}
}