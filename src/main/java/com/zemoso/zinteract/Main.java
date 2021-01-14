package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontable.DtResult;
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

		// String dTString="";
		// Properties prop = new Properties();
		// InputStream input = null;
		//
		// try {
		//
		// input = new FileInputStream("config.properties");
		//
		// // load a properties file
		// prop.load(input);
		// dTString = prop.getProperty("incomeTaxJson");
		//
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// } finally {
		// if (input != null) {
		// try {
		// input.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		String rules = "{ \"name\": \" Session check\", \"description\": \"To record session time\", \"artifact_id\": \"1\", \"options\": [ { \"propname\": \"exit_on_first_match\", \"propvalue\": \"true\" }, { \"propname\": \"ignore_case\", \"propvalue\": \"true\" } ], \"headers\": { \"conditions\": [ { \"condition\": \"eventName\" }, { \"condition\": \"stateExists\" }, { \"condition\": \"containsStartTime\" }, { \"condition\": \"containsEndTime\" } ], \"scripts\": [ { \"script\": \"sessionTimeout\" }, { \"script\": \"SessionTimeDiff\" } ], \"actions\": [ { \"action\": \"startTime\", \"type\": \"state\" }, { \"action\": \"endTime\", \"type\": \"state\" }, { \"action\": \"sessionId\", \"type\": \"state\" }, { \"action\": \"sessionLength\" } ] }, \"rows\": [ { \"conditions\": [ { \"condition\": \"eventName\", \"value\": \"~started\" }, { \"condition\": \"stateExists\", \"value\": \"false\" } ], \"actions\": [ { \"action\": \"startTime\", \"value\": \"<eventTime>\", \"scripted\": \"true\" }, { \"action\": \"sessionId\", \"value\": \"<sessionId>\", \"scripted\": \"true\" } ] }, { \"conditions\": [ { \"condition\": \"eventName\", \"value\": \"~started\" }, { \"condition\": \"stateExists\", \"value\": \"true\" }, { \"condition\": \"containsEndTime\", \"value\": \"false\" } ], \"actions\": [ { \"action\": \"startTime\", \"value\": \"<eventTime>\", \"scripted\": \"true\" }, { \"action\": \"sessionId\", \"value\": \"<sessionId>\", \"scripted\": \"true\" } ] }, { \"conditions\": [ { \"condition\": \"eventName\", \"value\": \"~started\" }, { \"condition\": \"stateExists\", \"value\": \"true\" }, { \"condition\": \"containsEndTime\", \"value\": \"true\" } ], \"scripts\": [ { \"name\": \"sessionTimeDiff\", \"script\": \"<eventTime>-<endTime>>15\" } ], \"actions\": [ { \"action\": \"startTime\", \"value\": \"<eventTime>\", \"scripted\": \"true\" }, { \"action\": \"sessionId\", \"value\": \"<sessionId>\", \"scripted\": \"true\" } ] }, { \"conditions\": [ { \"condition\": \"eventName\", \"value\": \"~started\" }, { \"condition\": \"stateExists\", \"value\": \"true\" }, { \"condition\": \"containsEndTime\", \"value\": \"true\" } ], \"scripts\": [ { \"script\": \"<eventTime>-<endTime><15\", \"name\": \"sessionTimeDiff\" } ], \"actions\": [ { \"action\": \"startTime\", \"value\": \"<startTime>\", \"scripted\": \"true\" }, { \"action\": \"sessionId\", \"value\": \"<stateSessionId>\", \"scripted\": \"true\" } ] }, { \"conditions\": [ { \"condition\": \"eventName\", \"value\": \"~ended\" }, { \"condition\": \"stateExists\", \"value\": \"true\" }, { \"condition\": \"containsStartTime\", \"value\": \"true\" } ], \"scripts\": [ { \"script\": \"<eventTime>-<startTime><3600\", \"name\": \"sessionTimeout\" } ], \"actions\": [ { \"action\": \"sessionId\", \"value\": \"<sessionId>\" }, { \"action\": \"startTime\", \"value\": \"<startTime>\", \"scripted\": \"true\" }, { \"action\": \"endTime\", \"value\": \"<eventTime>\", \"scripted\": \"true\" }, { \"action\": \"sessionLength\", \"value\": \"<eventTime>-<startTime>\", \"scripted\": \"true\" } ] }, { \"conditions\": [ { \"condition\": \"eventName\", \"value\": \"~ended\" }, { \"condition\": \"stateExists\", \"value\": \"true\" }, { \"condition\": \"containsStartTime\", \"value\": \"true\" } ], \"scripts\": [ { \"script\": \"<eventTime>-<startTime>>3600\", \"name\": \"sessionTimeout\" } ], \"actions\": [ { \"action\": \"sessionId\", \"value\": \"<sessionId>\" }, { \"action\": \"startTime\", \"value\": \"<startTime>\", \"scripted\": \"true\" }, { \"action\": \"endTime\", \"value\": \"<eventTime>\", \"scripted\": \"true\" }, { \"action\": \"sessionLength\", \"value\": \"3600\", \"scripted\": \"true\" } ] } ] }";
		HashMap<String, String> value = new HashMap<String, String>();
		value.put("containsStartTime", "true");
		value.put("stateExists", "true");
		value.put("eventTime", "1460718661");
		value.put("startTime", "1460718601");
		value.put("sessionId", "334353633");
		value.put("stateSessionId", "334353633");
		value.put("containsEndTime", "false");
		value.put("eventName", "zmobile.session_ended");
		LOGGER.info("Executing now");
		execute(value, rules);
	}

	public static void execute(HashMap<String, String> s, String json) {
		AbstractDtExecutorFactory Factory = AbstractDtExecutorFactory.getDtExecutorFactory();
		AbstractDtExecutor dtExecutor = Factory.getDtExecutor("dT_id1", json);
		// List<Map> results = dtExecutor.getAllActionResults(s);
		// for(Map result:results){
		// if(result.containsKey("tax")){
		// System.out.println("tax---"+((HashMap) result.get("tax")).get("value"));
		// }
		// if(result.containsKey("tax_before_cess")){
		// System.out.println("tax before cess"+((HashMap)
		// result.get("tax_before_cess")).get("value"));
		// }
		//
		// }
		DtResult result = dtExecutor.getFirstMatch(s);
		System.out.println(result.getActionResults());

	}

}
