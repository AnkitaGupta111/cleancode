package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDtExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDtExecutor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by deepak on 30/5/16.
 */
public class TaxCalculator {

	public static String getRulesJson() {
		String dTString = "";
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			dTString = prop.getProperty("incomeTaxJson");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if (input != null) {
				try {
					input.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dTString;
	}

	public static double execute(HashMap<String, String> s, String json) {
		AbstractDtExecutorFactory Factory = AbstractDtExecutorFactory.getDtExecutorFactory();
		AbstractDtExecutor dtExecutor = Factory.getDtExecutor("dT_id1", json);
		List<Map> results = dtExecutor.getAllActionResults(s);
		for (Map result : results) {
			if (result.containsKey("tax")) {
				System.out.println("tax---" + ((HashMap) result.get("tax")).get("value"));
				return Double.parseDouble(((HashMap) result.get("tax")).get("value").toString());
			}
		}
		return -1.00;
	}

}
