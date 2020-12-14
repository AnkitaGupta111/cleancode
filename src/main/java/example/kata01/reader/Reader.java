package example.kata01.reader;


import example.kata01.ruleApp.DisountRule;

import java.io.IOException;
import java.util.List;

public interface Reader {
     List<DisountRule> getRule(int itemId) throws IOException;
     String read();
}
