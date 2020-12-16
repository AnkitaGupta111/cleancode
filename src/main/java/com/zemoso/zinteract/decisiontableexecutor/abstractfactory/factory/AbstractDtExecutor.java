package com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Praveen on 05-Jan-15.
 */
public abstract class AbstractDtExecutor {
    public abstract DecisionTableResult getFirstMatch(Map<String,String> value);
    public abstract List<DecisionTableResult> getAllMatches(Map<String,String> value);
    public abstract Map getFirstMatchActionResults(Map<String,String> value);
    public abstract List<Map> getAllActionResults(Map<String,String> value);
}
