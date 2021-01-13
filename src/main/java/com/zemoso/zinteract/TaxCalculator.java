package com.zemoso.zinteract;

import com.zemoso.zinteract.constants.Constants;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.AbstractDecisionTableExecutorFactory;
import com.zemoso.zinteract.decisiontableexecutor.abstractfactory.factory.AbstractDecisionTableExecutor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * To Calculate the Tax income, by excluding the rebates under different sections.
 */
@Slf4j
public class TaxCalculator {

    /**
     * To get the rules from the persistent file.
     *
     * @return rules json
     */
    public static String getRulesJson() {
        String dTString = "";
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(Constants.CONFIG_FILE_NAME)) {
            prop.load(input);
            dTString = prop.getProperty(Constants.INCOME_TAX_JSON);
        } catch (IOException ex) {
            log.error("Error -->", ex.getMessage());
        }
        return dTString;
    }

    /**
     * Execute the decision table based on the
     * @param valueMap the value map
     * @param rules    the rules
     * and @return double double the tax to be paid.
     */
    public static double execute(Map<String, String> valueMap, String rules) {
        AbstractDecisionTableExecutorFactory abstractDecisionTableExecutorFactory = AbstractDecisionTableExecutorFactory.getExecutorFactory();
        AbstractDecisionTableExecutor dtExecutor = abstractDecisionTableExecutorFactory.getDecisionTableExecutor("dT_id1", rules);
        List<Map<String, Map<String, String>>> results = dtExecutor.getAllActionResults(valueMap);
        for (Map<String, Map<String, String>> result : results) {
            if (result.containsKey("tax")) {
                log.info("tax -->", (result.get("tax")).get("value"));
                return Double.parseDouble((result.get("tax")).get("value"));
            }
        }
        return -1.00;
    }

}
