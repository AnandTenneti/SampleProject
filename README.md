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

<h3>Folder Structure</h3>
The project is broadly organized in the following folder structure as shown below

pages: 
Each individual scenario in the website is marked as one page. Locators and methods of each 
scenario are added in the page
  Example:
     The scenario Visibility is considered as one page and the elements in the 
 Visibility page are identified with locators and the corresponding functions for the actions to 
be performed are written in Visibility page

tests:

dataprovider:

reports:
The test results of the tests executed in the automation run are stored under the reports folder.




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
6. **Test Reporting**: 

   The reports for the automation test results can be viewed under the reports 
   folder in the index.html file

