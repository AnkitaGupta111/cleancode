package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDtExecutor;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDtExecutorFactory;
import org.apache.log4j.Logger;

import java.util.HashMap;


public class DecisionTableGenerator {
    private static Logger LOGGER = Logger.getLogger(DecisionTableGenerator.class);


    public static DecisionTableResult execute(HashMap<String, String> s, String json){
        AbstractDtExecutorFactory Factory = AbstractDtExecutorFactory.getDtExecutorFactory();
        AbstractDtExecutor dtExecutor = Factory.getDtExecutor("dT_id1",json);
//        List<Map> results = dtExecutor.getAllActionResults(s);
//        for(Map result:results){
//            if(result.containsKey("tax")){
//                System.out.println("tax---"+((HashMap) result.get("tax")).get("value"));
//            }
//            if(result.containsKey("tax_before_cess")){
//                System.out.println("tax before cess"+((HashMap) result.get("tax_before_cess")).get("value"));
//            }
//
//        }
        DecisionTableResult result=dtExecutor.getFirstMatch(s);
        System.out.println(result.getActionResults());
        return result;
    }

}











































