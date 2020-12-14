package example.kata01.ruleApp;

import example.kata01.reader.FileRuleReader;
import example.kata01.reader.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class RuleManager {

    Reader ruleReader;
    Properties props;

    public void initialize() {
        try {
            props = new Properties();
            InputStream inputStream = new FileInputStream("/home/pavan/Desktop/supermart/src/main/resources/config.properties");
            props.load(inputStream);
            if(props.get("input.source").toString().equalsIgnoreCase("file")) {
                ruleReader = new FileRuleReader(new File("/home/pavan/Desktop/supermart/src/main/resources/items.json"));
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }

    public List<DisountRule> getRule(int itemId) {
        try {
            return ruleReader.getRule(itemId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
