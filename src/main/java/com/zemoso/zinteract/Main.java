package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontableexecutor.AbstractDtExecutor;
import com.zemoso.zinteract.decisiontableexecutor.AbstractDtExecutorFactory;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        String dTString="";
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);
            dTString = prop.getProperty("incomeTaxJson");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        HashMap<String,String> value = new HashMap<String,String>();
          value.put("investment_80c","10000");
          value.put("income","510000");
          value.put("tax_before_cess","0");
        LOGGER.info("Executing now");
        execute(value, dTString);
    }

    public static void execute(HashMap<String, String> s, String json){
        AbstractDtExecutorFactory Factory = AbstractDtExecutorFactory.getDtExecutorFactory();
        AbstractDtExecutor dtExecutor = Factory.getDtExecutor("dT_id1",json);
        List<Map> results = dtExecutor.getAllActionResults(s);
        for(Map result:results){
            if(result.containsKey("tax")){
                System.out.println("tax---"+((HashMap) result.get("tax")).get("value"));
            }
            if(result.containsKey("tax_before_cess")){
                System.out.println("tax before cess"+((HashMap) result.get("tax_before_cess")).get("value"));
            }

        }

    }

}











































