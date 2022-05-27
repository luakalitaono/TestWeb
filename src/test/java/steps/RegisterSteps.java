package steps;

import config.DriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Date;

public class RegisterSteps extends DriverConfig {

    @Before
    public void setUp() {
        super.initDriver();
    }

    @After
    public void tearDown() {
        super.closeDriver();
    }

    @BeforeStep
    public void awaitEachStep() {
        wait(1);
    }

    @Given("^user is in home page$")
    public void userIsInHomePage() {
        accessHomePage();
        waitUntil(ExpectedConditions.elementToBeClickable(findByClass("login")));
    }

    @When("^user goes to register page$")
    public void userGoesToRegisterPage() {
        findByClass("login").click();
        wait(4);
        waitUntil(ExpectedConditions.elementToBeClickable(findById("email_create")));
    }

    @And("^user in register page inserts email '(.*?)'$")
    public void userInsertsEmail(String email) {
        final long timeInMillis = new Date().getTime();
        final String modifiedEmail = email.replaceAll("@", timeInMillis + "@");
        findById("email_create").sendKeys(modifiedEmail);
    }

    @And("^user press Create an account button$")
    public void userPressCreateButton() {
        findById("SubmitCreate").click();
        wait(4);
    }

    @Then("^detailed form appears and user inserts data '(.*?)'$")
    public void userInsertsDetailedData(String name) {
        waitUntil(ExpectedConditions.visibilityOf(findById("customer_firstname")));
        insertFormData(name, "register.yml");
    }

    @And("^press register button$")
    public void userPressRegisterButton() {
        findById("submitAccount").click();
        wait(4);
    }

    @Then("^logged home page is shown")
    public void loggedHomeIsShown() {
        waitUntil(ExpectedConditions.visibilityOf(findByClass("info-account")));
        Assert.assertTrue(findByClass("info-account").isDisplayed());
        wait(2);
    }

    @Then("^email error message appears$")
    public void emailErrorMessageAppears () {
        waitUntil(ExpectedConditions.visibilityOf(findById("create_account_error")));
        wait(2);
    }

    @Then("^fields error message appears$")
    public void fieldsErrorMessageAppears() {
        waitUntil(ExpectedConditions.visibilityOf(findByClass("alert-danger")));
        wait(2);
    }

}
