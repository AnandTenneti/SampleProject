<h1>UI Test Automation Playground</h1>
<h2>Introduction</h2>

**
In the reference site  <a href="http://uitestingplayground.com/">UI Test Automation 
Playground</a>, different scenarios are provided for automation. 

Designed and implemented an automation framework covering the scenarios using WebDriver and Java
**

<h2>Installation Steps</h2>

1. Fork the repository

2. Clone the repository, i.e download your copy of the repository to your local machine

       git clone https://github.com/AnandTenneti/SampleProject.git

3. Import the changes in IntelliJIdea

4. Make your desired changes

5. Use IntelliJ IDEA to run your desired tests. Alternatively, you can use the terminal
 to run the tests.For example , to run all the tests using the firefox browser in headful mode
   
       mvn clean test -Dbrowser=firefox
   
6. To see the report, go to the testoutput folder in the project root and then go to the report folder.


<h2>Languages and Frameworks</h2>
The project uses the following:
 

   * Java11 as the programming language
   * Selenium WebDriver as the browser automation framework using Java binding
   * <a href="https://testng.org/">TestNG</a> as the testing framework
   * <a href="https://maven.apache.org/">Maven</a> as the Java build tool
   * IntellijIdea as the IDE







It sounds like you're working on a UI automation testing project that involves creating automated scripts using Java and WebDriver (likely Selenium WebDriver). If you'd like help with any specific aspect of your project—like writing test scripts, optimizing tests, handling locators, dealing with dynamic elements, or setting up the framework—feel free to ask!

For example, if you're starting fresh, a basic framework might include:

Page Object Model (POM) for organizing your locators and methods.
TestNG or JUnit for managing test cases and assertions.
Maven or Gradle for dependency management.
Logging and Reporting (e.g., using Log4j or ExtentReports).

<h3>Project Structure</h3>

SampleProject<br>
|<br>
|--pages
<h3>Folder Structure</h3>
The project is organized in the following folder structure as shown below

utils:
All the utility functions are defined

<b>pom.xml</b>
All the dependencies of the project are specified within pom.xml file

testng.xml

<b>pages:</b> 

Within this folder, all the pages and the elements within the pages and the actions/functions of 
each page are provided

tests:
Within this folder, the tests are written 

Screenshots:
Within this folder, screenshots of the pages of failed test cases are stored 


   
 
6. **Prerequisites**: Requirements that the user needs to have installed.
     Java
     WebDriver
     TestNG
3. **Setup**: Instructions on how to set up the project, including how to clone the repository,
   install dependencies, and configure WebDriver.

4. **Usage**: Provides instructions on how to write test cases and configure the project for
   different browsers.
5. **Running Tests**: Describes how to execute the tests using Maven or an IDE.
   The tests are run using the maven commands
   
   -- run the whole test suite
             
       mvn clean test

   -- run all the tests in a class

       mvn clean test -Dtest=UiElementsTest6 
   
   -- run a single test in a class
          
       mvn clean test -Dtest=UiElementsTest6#test_autoWait

   -- run tests in a specified browser
   
       mvn clean test -Dbrowser=firefox

   By default, if no browser is specified in the command line, the tests will run in chromw
   browser in headless mode
6. **Test Reporting**: Explains where users can find the test results and reports.
