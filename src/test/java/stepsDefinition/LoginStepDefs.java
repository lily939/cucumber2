package stepsDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginStepDefs {
    LoginPage loginPage = new LoginPage();

    @Given("The user go to page with url {string}")
    public void theUserGoToPageWithUrl(String url) {
        loginPage.openPage(url);
    }

    @When("The user input username {string} and password {string}")
    public void theUserInputUsernameAndPassword(String userName, String password) {
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
    }

    @Then("Go to page with title {string} and url {string}")
    public void goToPageWithTitleAndUrl(String title, String url) {
        loginPage.verifyTitlePageAsExpectedResult(title);
        Assert.assertEquals(loginPage.getUrl(), url);
    }

    @And("The user click on Login")
    public void theUserClickOnLogin() {
        loginPage.clickLoginButton();
    }
}
