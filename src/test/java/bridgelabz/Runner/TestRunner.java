package bridgelabz.Runner;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features",
//        glue = {"bridgelabz.StepDefinations", "bridgelabz.hooks"},
//        plugin = {
//                "pretty",
//                "html:target/CucumberReports/cucumber-report.html",
//                "json:target/CucumberReports/cucumber.json",
//                "junit:target/CucumberReports/cucumber.xml"
//        },
//        tags = "@search"
//)
//public class TestRunner {
//
//}

// TestNG Implementation

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"bridgelabz.StepDefinations", "bridgelabz.hooks"},
        plugin = {
                "pretty",
                "html:target/CucumberReports/cucumber-report.html",
                "json:target/CucumberReports/cucumber.json",
                "junit:target/CucumberReports/cucumber.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@login_TC_001"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}