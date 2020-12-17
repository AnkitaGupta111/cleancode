# Zinteract
Features
1. Parse input JSON and create complete DecisionTable Model
2. Set the Conditions as per the pattern defined in the input json
    ex:  { "condition": "eventName",  "value": "~ended"}  - means eventName should contain "ended" ("Like" in Sql) String.
3. Script tags evaluation

implementations:
1. PatternMatcher will hold definitions of all the conditional patterns with which we can define different conditions
2. DecisionTableCreator is responsible for creating DecisionTable with the given input json
3. Different kind`s of comparators are like EqualsComparator GreaterThanComparator etc.. available to evaluate the conditions defined using PatternMatcher.
4. DecisionTableExecutor getAllMatches will give the all matching rows which satisfies the given condition