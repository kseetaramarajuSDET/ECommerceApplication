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

import bridgelabz.driver.BrowserContext;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(features = "src/test/resources/features", glue = {"bridgelabz.StepDefinations", "bridgelabz.hooks"}, plugin = {"pretty", "html:target/CucumberReports/cucumber-report.html", "json:target/CucumberReports/cucumber.json", "junit:target/CucumberReports/cucumber.xml", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
        , tags = "@all"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}