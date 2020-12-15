package com.zemoso.zinteract;

import com.zemoso.zinteract.constants.Constant;
import com.zemoso.zinteract.decisiontableexecutor.AbstractDtExecutor;
import com.zemoso.zinteract.decisiontableexecutor.AbstractDtExecutorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@Slf4j
public class TaxCalTest {

    @Test
    public void testTaxCalBelow25K() {
        String json = getRulesJson();
        HashMap<String, String> value = new HashMap<>();
        value.put("investment_80c", "1000");
        value.put("income", "249000");
        double tax = execute(value, json);
        assertEquals(0, tax, 0.0);
    }


    @Test
    public void testTaxCal25KTo50K() {
        String json = getRulesJson();
        HashMap<String, String> value = new HashMap<>();
        value.put("investment_80c", "30000");
        value.put("income", "310000");
        double tax = execute(value, json);
        assertEquals(1030.20, tax, 0.0);
    }

    @Test
    public void testTaxCal50kTo100K() {
        String json = getRulesJson();
        HashMap<String, String> value = new HashMap<>();
        value.put("investment_80c", "10000");
        value.put("income", "510000");
        double tax = execute(value, json);
        assertEquals(23694.60, tax, 0.0);
    }

    @Test
    public void testTaxCalAbove100K() {
        String json = getRulesJson();
        HashMap<String, String> value = new HashMap<>();
        value.put("investment_80c", "10000");
        value.put("income", "1100000");
        double tax = execute(value, json);
        assertEquals(156590.4, tax, 0.0);
    }

    private String getRulesJson() {
        String dTString = "";
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(Constant.CONFIG_FILE);
            // load a properties file
            prop.load(input);
            dTString = prop.getProperty(Constant.INCOME_TAX_JSON);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return dTString;
    }

    private double execute(HashMap<String, String> s, String json) {
        AbstractDtExecutorFactory Factory = AbstractDtExecutorFactory.getDtExecutorFactory();
        AbstractDtExecutor dtExecutor = Factory.getDtExecutor("dT_id1", json);
        List<Map<String, Map<String, String>>> results = dtExecutor.getAllActionResults(s);
        for (Map<String, Map<String, String>> result : results) {
            if (result.containsKey("tax")) {
                log.info("tax---" + (result.get("tax")).get("value"));
                return Double.parseDouble((result.get("tax")).get("value"));
            }
        }
        return -1.00;
    }


}
