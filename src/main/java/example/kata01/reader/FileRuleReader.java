package example.kata01.reader;

import example.kata01.ruleApp.DisountRule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


public class FileRuleReader implements Reader {
    File file;
    Map<Integer, List<DisountRule>>  rules = new HashMap();

    public FileRuleReader(File file) {
        this.file = file;
    }

    @Override
    public List<DisountRule> getRule(int itemId) throws IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        JSONArray jsonArray = new JSONArray(content);
        Iterator<Object> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            if( jsonObject.get("itemId").equals(itemId) ) {
                int ruleId = Integer.parseInt(jsonObject.get("ruleId").toString());
                File ruleFile = new File("/home/pavan/Desktop/supermart/src/main/resources/rules.json");
                String ruleContent = new String(Files.readAllBytes(ruleFile.toPath()));
                JSONArray jsonArray1 = new JSONArray(ruleContent);
                Iterator<Object> iterator1 = jsonArray1.iterator();
                List<DisountRule> disountRules = new ArrayList<>();
                while(iterator1.hasNext()) {
                    JSONObject rule = (JSONObject) iterator1.next();

                    DisountRule disountRuleBuilder = new DisountRule();
                    if(rule.has("discountPercentage")) {
                        disountRuleBuilder.setDiscountPercentage(Optional.of(Integer.parseInt(rule.get("discountPercentage").toString())));
                    }
                    if(rule.has("discountPrice")) {
                        disountRuleBuilder.setDiscountPrice(Optional.of(Integer.parseInt(rule.get("discountPrice").toString())));
                    }
                    if(rule.has("orderQuantity")) {
                        disountRuleBuilder.setOrderQuantity(Optional.of(Integer.parseInt(rule.get("orderQuantity").toString())));
                    }
                    if(rule.has("discountQuantity")) {
                        disountRuleBuilder.setDiscountQuantity(Optional.of(Integer.parseInt(rule.get("discountQuantity").toString())));
                    }
                    if(rule.has("ruleId")) {
                        disountRuleBuilder.setRuleId(Integer.parseInt(rule.get("ruleId").toString()));
                    }
                    if(Integer.parseInt(rule.get("ruleId").toString()) == ruleId) {
                        disountRules.add(disountRuleBuilder);
                    }
                }
                rules.put(itemId, disountRules);
            }
        }
        return rules.get(itemId);
    }

    @Override
    public String read() {
        return "";
    }
}
