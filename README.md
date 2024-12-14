<h1>UI Test Automation Playground</h1>
<h2>Introduction</h2>
In this reference site <a href="http://uitestingplayground.com/">UI Test Automation 
Playground</a>,there are different UI automation scenarios provided for which the automation 
test cases have been written using Selenium WebDriver and Java using Maven as the build tool and 
TestNG  
**The purpose of this website is to provide a platform for sharpening UI test automation 
skills. 
Use it to practice with your test automation tool. Use it to learn test automation techniques.
Different automation pitfalls appearing in modern web applications are described and emulated 
below.**
**
It sounds like you're working on a UI automation testing project that involves creating automated scripts using Java and WebDriver (likely Selenium WebDriver). If you'd like help with any specific aspect of your project—like writing test scripts, optimizing tests, handling locators, dealing with dynamic elements, or setting up the framework—feel free to ask!

For example, if you're starting fresh, a basic framework might include:

Page Object Model (POM) for organizing your locators and methods.
TestNG or JUnit for managing test cases and assertions.
Maven or Gradle for dependency management.
Logging and Reporting (e.g., using Log4j or ExtentReports).

**
The purpose of this website is to provide a platform for sharpening UI test automation skills.
Use it to practice with your test automation tool. Use it to learn test automation techniques.

Different automation pitfalls appearing in modern web applications are described and emulated below.

<h3>Project Structure</h3>

SampleProject<br>
|<br>
|--pages
<h3>Folder Structure</h3>
The project is organized in the following folder structure as shown below

utils:
All the utility functions are defined

pom.xml
All the dependencies of the project are specified within pom.xml file

testng.xml

pages: 
Within this folder, all the pages and the elements within the pages and the actions/functions of 
each page are provided

tests:
Within this folder, the tests are written 

Screenshots:
Within this folder, screenshots of the pages of failed test cases are stored 
<h3>Page Objects</h3>
<h3></h3>
<table border-width=0>
  <tbody>
    <tr><td><a href="#">Dynamic ID</a></td><td><a href="#">Class Attribute</a></td><td><a href="#">Hidden Layers</a></td><td><a href="#">Load Delay</a></td></tr>
    <tr><td><a href="#">Ajax Data</a></td><td><a href="#">Client Side Delay</a></td><td><a href="#">Click</a></td><td><a href="#">Text Input</a></td></tr>
     <tr><td><a href="#">Scrollbars</a></td><td><a href="#">Dynamic Table</a></td><td><a href="#">Verify Text</a></td><td><a href="#">Progress Bar</a></td></tr>
     <tr><td><a href="#">Visibility</a></td><td><a href="#">Sample App</a></td><td><a href="#">Mouse Over</a></td><td><a href="#">Non-Breaking Space</a></td></tr>
     <tr><td><a href="#" style="color:#FF0000;">Overlapped Element</a></td><td><a href="#">Shadow DOM</a></td><td><a href="#">Alerts</a></td><td><a href="#">File Upload</a></td></tr>
     <tr><td><a href="#">Animated Button</a></td><td><a href="#">Disabled Input</a></td><td><a href="#" style="color: black; text-decoration: underline;text-decoration-style: dotted;">Auto Wait</a></td></tr>
  </tbody>
</table>
<table align="right">
  <tr>
  <td>Total No. of Test Cases</td><td>23</td>
  </tr>
   <tr>
  <td>No. of Test Cases Executed</td><td>12</td>
  </tr>
   <tr>
  <td>No. of Test Cases Remaining</td><td>11</td>
  </tr>
</table>

1. **Project Overview**: A brief introduction to the project and what it does.
2. **Prerequisites**: Requirements that the user needs to have installed.
     Java
     WebDriver
     TestNG
3. **Setup**: Instructions on how to set up the project, including how to clone the repository,
   install dependencies, and configure WebDriver.
4. **Usage**: Provides instructions on how to write test cases and configure the project for
   different browsers.
5. **Running Tests**: Describes how to execute the tests using Maven or an IDE.
   The tests are run using the maven commands
   To run the whole test suite
   mvn clean test
   To run all the tests in a class
   mvn clean test -Dtest=UiElementsTest6
   To run a single test in a class
   mvn clean test -Dtest=UiElementsTest6#test_autoWait
   To run tests in a specified browser
   mvn clean test -Dbrowser=firefox
   By defualt, if no browser is specified in the command line, the tests will run in chromw
   browser in headless mode
6. **Test Reporting**: Explains where users can find the test results and reports.