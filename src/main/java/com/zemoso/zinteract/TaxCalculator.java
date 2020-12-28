package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by deepak on 30/5/16.
 */
public class TaxCalculator {

	private static Logger log = LoggerFactory.getLogger(TaxCalculator.class);

	public static String getRulesJson() {
		String dTString = "";
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("config.properties");) {
			prop.load(input);
			dTString = prop.getProperty("incomeTaxJson");
		} catch (FileNotFoundException e) {
			log.error("Cannot load properties");
		} catch (IOException e) {
			log.error("Cannot load properties");
		}
		return dTString;
	}

	public static double execute(HashMap<String, String> s, String json) {
		AbstractDecisionTableExecutorFactory Factory = AbstractDecisionTableExecutorFactory.getDtExecutorFactory();
		AbstractDecisionTableExecutor dtExecutor = Factory.getDecisionTableExecutor("dT_id1", json);
		List<Map> results = dtExecutor.getAllActionResults(s);
		for (Map result : results) {
			if (result.containsKey("tax")) {
				log.debug("tax---" + ((HashMap) result.get("tax")).get("value"));
				return Double.parseDouble(((HashMap) result.get("tax")).get("value").toString());
			}
		}
		return -1.00;
	}

}
