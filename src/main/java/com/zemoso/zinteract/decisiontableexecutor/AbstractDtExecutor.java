package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.decisiontable.DtRow;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Praveen on 05-Jan-15.
 */
public abstract class AbstractDtExecutor {
    public abstract DtRow getFirstMatch(HashMap<String,String> value);
    public abstract ArrayList<DtRow> getAllMatches(HashMap<String,String> value);
}
