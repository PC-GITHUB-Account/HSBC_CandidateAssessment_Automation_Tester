package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/CurrExchangeRate.feature",
	 			 plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json", "junit:target/cucumber-reports/cucumber.xml", "rerun:target/cucumber-reports/rerun.txt"},
	 			 glue = {"stepDefinations"},
	 			 tags = "@RegressionTest",
	 			 monochrome= true,
	 			 dryRun= false,
	 			 strict = true)


public class TestRuner {

}
