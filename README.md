# Description
Tax-Calculation made easy

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Built with
Spring Boot

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Main` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Input
This project expects a JSON file in which you can write your rules(please check the Support section).

## Output
Based on the given rules, the code will perform the operations and will provide the appropriate results.

## Explanation
1. This project will read the input json file.
2. Based on the Input JSON file, It will create a Decision table. In which it will create the headers, scripts, conditions and actions respectively by reading the input JSON file.
3. The operations will be performed on the conditions and actions mapped to the conditions of headers.

## Support
The supported conditions(like less than, greater than... etc) are mentioned in the com.zemoso.zinteract.decisiontable.PatternMatcher class. You can add the missing operations here.

 ## Important Files to look in
 <b>Decision Table(DecisionTable.java)</b> : Responsible to create the Object just like the input JSON.
 <br>
 <b>Data Action(DtAction.java)</b> : Responsible to create the action along with the scripted field.
 <br>
 <b>Data Row(DtRow.java)</b> : Responsible to create the row objects by reading the input JSON file.

 ### Getting Started with Example
 You can find the sample input JSON file in config.properties



## Copyright

Released by ZemosoLabs( [Visit](https://www.zemosolabs.com/) ).