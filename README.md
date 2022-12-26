Key highlights:

1.Used Maven profiling
2.created Pojo classes for request creation and serialization
3.created singleton class to access properties
4.created Cucumber style BDD testcases

things to improve :

1.Testdata generation can be improved ,read the testdata from external Db,excel etc.
2.Add checkstyle and Sonarlint for to improve quality

Steps to Run tests:


1.clone the repo and assuming you have intelijidea or eclispse,mvn  setup in your local machine 
2`mvn test` or Navigate to src/test/java/runner right click on TestRunner class and run.

Reports can be found here:

target/cucumber-html-report.html   

Or

you can find the reports here as well:


Then Verify if pet is deleted                                               # stepdefination.petstoresteps.verify_if_pet_is_deleted()
????????????????????????????????????????????????????????????????????????????
? View your Cucumber Report at:                                            ?
? https://reports.cucumber.io/reports/6ac35d9c-5bb8-46a1-8cc7-ed143565b8e2 ?
?                                                                          ?
? This report will self-destruct in 24h.                                   ?
? Keep reports forever: https://reports.cucumber.io/profile                ?

Explanation about the project structure:

1.all feature files can be found here
src/test/java/features

2.stepdefinations can be found here
src/test/java/stepdefination

3.runner can be found here
src/test/java/runner



