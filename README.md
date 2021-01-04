## About the Project
Any complex decision problem using JSON can be easily solved with this Project.

## Built with
Spring Boot

## Input
This project expects a JSON file in which you can write your rules(please check the Support section).

## Output
Based on the given rules, the code will perform the operations and will provide the appropriate results.

## Explanation
1. This project will read the input json file.
2. Based on the Input JSON file, It will create a Decision table. In which it will create the headers, scripts, conditions and actions respectively by reading the input JSON file.
3. The operations will be performed on the conditions and actions mapped to the conditions of headers.

## Support
The supported conditions(like less than, greater than etc) are mentioned in the com.zemoso.zinteract.decisiontable.PatternMatcher class. You can add the missing operations here.

 ## Important Files to look in
 <b>Decision Table(DecisionTable.java)</b> : Responsible to create the Object just like the input JSON.
 <br>
 <b>Data Action(DtAction.java)</b> : Responsible to create the action along with the scripted field.
 <br>
 <b>Data Row(DtRow.java)</b> : Responsible to create the row objects by reading the input JSON file.
 
 ### Getting Started with Example
 You can find the sample input JSON file in config.properties 

 
## License
Zemoso labs


=======
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

# Relational
 For Database design,
 visit:<link>https://dbdiagram.io/d/5fe4778d9a6c525a03bc3652</link>
