package steps;

import config.DriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShopSteps extends DriverConfig {

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

    @Given("^user is signed in successfully with data '(.*)'$")
    public void userSignsInSuccessfullyWithData(String param) {
        accessHomePage();
        wait(4);
        WebElement tag = findByClass("login");
        waitUntil(ExpectedConditions.elementToBeClickable(tag));
        tag.click();
        wait(4);
        waitUntil(ExpectedConditions.visibilityOf(findById("email")));
        insertFormData(param, "login.yml");
        wait(2);
        findById("SubmitLogin").click();
    }

    @Then("^user access list page and select item '(.*)'")
    public void userGoesToListPageAndSelectList(String item) {
        findByXpath("//a[starts-with(@title, 'My Store')]").click();
        wait(2);
        selectsNextItem(item);
    }

    @Then("^selects next item '(.*)'")
    public void selectsNextItem(String item) {
        waitUntil(ExpectedConditions.visibilityOf(findById("home-page-tabs")));
        findByXpath("//img[starts-with(@alt, '" + item + "')]").click();
        wait(4);
        switchToFrame(findByClass("fancybox-iframe"));
    }

    public void userFillsData(String param) {
        waitUntil(ExpectedConditions.visibilityOf(findById("add_to_cart")));
        insertFormData(param, "shop.yml");
    }

    @Then("^user selects next item '(.*)' and fills data '(.*)'")
    public void selectNextItemAndFills(String item, String param) {
        selectsNextItem(item);
        userFillsData(param);
    }

    @Then("^user goes to list page and select item '(.*)' and fills data '(.*)'$")
    public void goesToLisPageSelectAndFills(String item, String param) {
        userGoesToListPageAndSelectList(item);
        userFillsData(param);
    }

    @And("^press add to cart$")
    public void pressAddToCart() {
        findByName("Submit").click();
        switchToDefault();
        wait(4);
        WebElement close = findByClass("cross");
        waitUntil(ExpectedConditions.visibilityOf(close));
        close.click();
        wait(2);
    }

    @And("^proceed to checkout$")
    public void proceedToCheckout() {
        findByName("Submit").click();
        switchToDefault();
        wait(4);
        findByXpath("//a[starts-with(@title, 'Proceed to checkout')]").click();
        wait(4);
    }

    @And("^user follows steps until complete the shop$")
    public void userFollowsStepsUntilComplete() {
        waitUntil(ExpectedConditions.visibilityOf(findById("order_step")));
        findByClass("standard-checkout").click();
        wait(2);

//        waitUntil(ExpectedConditions.visibilityOf(findById("id_address_delivery")));
        findByName("processAddress").click();
        wait(2);

//        waitUntil(ExpectedConditions.visibilityOf(findByName("carrier_area")));
        findById("uniform-cgv").click();
        wait(1);
        findByName("processCarrier").click();
        wait(2);

        waitUntil(ExpectedConditions.visibilityOf(findById("HOOK_PAYMENT")));
        findByXpath("//a[starts-with(@title, 'Pay by bank wire')]").click();
        wait(2);

        WebElement submit = findByXpath("//p[starts-with(@id,'cart_navigation')]//button");
        waitUntil(ExpectedConditions.visibilityOf(submit));
        submit.click();
        wait(2);
    }

    @Then("^the message order confirmation should appear$")
    public void messageOrderConfirmationShouldAppear() {
        Assert.assertEquals("ORDER CONFIRMATION", findByClass("page-heading").getText());
    }

}
