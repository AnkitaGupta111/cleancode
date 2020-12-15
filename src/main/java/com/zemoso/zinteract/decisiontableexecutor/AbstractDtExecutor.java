package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.decisiontable.DtResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Praveen on 05-Jan-15.
 */
public abstract class AbstractDtExecutor {
    public abstract DtResult getFirstMatch(Map<String,String> value);
    public abstract List<DtResult> getAllMatches(Map<String,String> value);
    public abstract List<Map<String, Map<String, String>>> getAllActionResults(Map<String,String> value);
}
