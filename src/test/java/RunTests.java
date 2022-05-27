
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty", "html:build/reports/tests/cucumber/cucumber-report.html"},
        glue = {"steps"},
        features = {"src/test/resources/features"}
)
public class RunTests {
}
