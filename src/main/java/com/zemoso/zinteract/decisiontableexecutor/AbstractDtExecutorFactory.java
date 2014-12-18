package com.zemoso.zinteract.decisiontableexecutor;


public abstract class AbstractDtExecutorFactory {
	public abstract DtExecutor getDtExecutor(String dT_id, String json);
}
