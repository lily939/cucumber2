package core.cucumber.runner;

import core.WebRunner;
import cucumber.api.CucumberOptions;
@CucumberOptions(
        features = {"src/test/resources/features/Login.feature"},
        glue = {"stepsDefinition"},
        monochrome = true, strict = true)
public class LoginTest extends WebRunner {
}

