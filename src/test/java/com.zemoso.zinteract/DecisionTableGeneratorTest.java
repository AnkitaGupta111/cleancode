package com.zemoso.zinteract;

import com.zemoso.zinteract.decisiontable.DecisionTableResult;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.zemoso.zinteract.DecisionTableGenerator.execute;

public class DecisionTableGeneratorTest {

	@Test
	void testGetActionRuleSet() {
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
		DecisionTableResult result = execute(value, rules);
		Assertions.assertNotNull(result);
	}

}
