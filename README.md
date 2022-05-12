# Gegidze QA Engineer Automation Task
## Testing Strategy:
This project was completed approximately in 3 days.
Before automation, every test cases was tested manually and the ones only passed 
against manually was tested automatically. Since there is no document about requirements, 
expected result was created based on the website. After all test are done, 
login credentials was changed, so it will be failed after that. 
There is an excel file that includes test scenarios for manual test
There are some differences between manual and automation test steps.
Since there is no visual evidence on the browser for API test, it wasn't recorded. 
</br>The video is for Currency Exchange feeture is here: https://screenrec.com/share/zXctOC25nq 
</br>and for login feature is here:https://screenrec.com/share/oOR0Emk7Zg 
</br> </br> In order to see the test steps in the video, scroll methods are used. 
This caused the execution time to be extended.



### Following tools were used in this framework:
Maven, JUnit, Selenium WebDriver, RestAssured Library, Cucumber Reports,
All automation is done in Java.


### Project Design:
* Page Object Model (POM) design pattern
* Singleton design pattern
* DDT with scenario outline
* Have a supporting utilities package in *src/test/java/com/paysera* file path, named ***"utilities"*** that includes classes which services as a core engine for the project
* Special for this task I prefer to put framework and tests in the same place

### How to run the project main test cases locally:
* A properties file ***"configuration.properties"*** can be found it *configuration.properties* file path including all the configurations needed in the execution
* Can find the test cases in the *src/test/resources* folder mainly in the *features* package
* Can find the *report.html* in the *test-output/Screenshots* folder for failed test scenarios

