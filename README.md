<h1>UI Test Automation Playground</h1>
<h2>Introduction</h2>

**
In the reference site  <a href="http://uitestingplayground.com/">UI Test Automation 
Playground</a>, different scenarios are provided for automation. 

Designed and implemented an automation framework covering the scenarios using WebDriver and Java
**

<h2>1. Installation Steps</h2>

* Fork the repository

* Clone the repository, i.e download your copy of the repository to your local machine

       git clone https://github.com/AnandTenneti/SampleProject.git

* Import the changes in IntelliJIdea

* Make your desired changes

* Use IntelliJ IDEA to run your desired tests. Alternatively, you can use the terminal
 to run the tests.For example , to run all the tests using the firefox browser in headful mode
   
       mvn clean test -Dbrowser=firefox
   
* To see the report, go to the testoutput folder in the project root and then go to the report 
folder.


<h2>2. Languages and Frameworks</h2>

The project uses the following:

 

   * [Java](https://www.java.com/en/) as the programming language
   * [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/) as the browser automation framework using Java binding
   * [TestNG](https://testng.org/) as the testing framework
   * [Maven](https://maven.apache.org/) as the Java build tool
   * [IntelliJIdea](https://www.jetbrains.com/idea/) as the IDE

<h2>3.Folder Structure</h2>

The project is broadly organized in the following folder structure as shown below


**pages:** 
Each individual scenario in the website is marked as one page. Locators and methods of each 
scenario are added in the page

  Example:
     The scenario Visibility is considered as one page and the elements in the 
 Visibility page are identified with locators and the corresponding functions for the actions to 
be performed are written in Visibility page

**tests:**

**dataprovider:**

**reports:**

The test results of the tests executed in the automation run are stored under the reports folder.




utils:
All the utility functions are defined

<b>pom.xml</b>
All the dependencies of the project are specified within pom.xml file

testng.xml


**Screenshots:**

Screenshots of the failed test cases are captured and stored within the Screenshots folder under 
a subfolder created for each run. This way, the screenshots are archived and can be viewed at a 
later point of time

<h2>4.Running Tests</h2> Describes how to execute the tests using Maven or an IDE.
   
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

