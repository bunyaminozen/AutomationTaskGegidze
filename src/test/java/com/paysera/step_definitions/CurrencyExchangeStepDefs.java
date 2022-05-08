package com.paysera.step_definitions;

import com.paysera.pages.CurrencyExchangePage;

import static com.paysera.utilities.BrowserUtils.*;

import com.paysera.utilities.ConfigurationReader;
import com.paysera.utilities.Driver;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeStepDefs {
    CurrencyExchangePage currencyExchange = new CurrencyExchangePage();
    Actions actions = new Actions(Driver.get());
    String firstUSDPayseraRate; //created a global variable in order to check if it changes depends on respective currency

    @Given("the user is on the {string} page;")
    public void the_user_is_on_the_page(String pageName) {
        Driver.get().get(ConfigurationReader.get(pageName));
        waitForPageToLoad(10);
        //assigned the text to global variable in order to assert with the new one
        firstUSDPayseraRate = currencyExchange.USDPayseraRate.getText();
    }

    @Then("the {string} amount input box should be filled")
    public void the_amount_input_box_should_be_filled(String boxName) {
        WebElement inputBox = currencyExchange.getInputBox(boxName);
        actions.moveToElement(inputBox).perform();
        waitFor(3);
        String value = inputBox.getAttribute("value");
        assertFalse(value.isEmpty());

    }

    @Then("the {string} amount input box should be empty")
    public void the_amount_input_box_should_be_empty(String boxName) {
        WebElement inputBox = currencyExchange.getInputBox(boxName);
        String value = inputBox.getAttribute("value");
        assertTrue(value.isEmpty());
    }

    @When("the user enter {string} into the {string} amount input box")
    public void the_user_enter_an_amount_into_the_amount_input_box(String amount, String boxName) {
        WebElement inputBox = currencyExchange.getInputBox(boxName);
        inputBox.clear();
        inputBox.sendKeys(amount);
        waitFor(1);
    }

    @Then("{string} should be displayed as respective currency")
    public void should_be_displayed_as_respective_currency(String expectedCurrency) {
        try {
            currencyExchange.acceptCookies.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            actions.moveToElement(currencyExchange.sellCurrencyOption).perform();
            waitFor(3);
            String actualCurrency = currencyExchange.sellCurrencyOption.getText();
            assertEquals("Verify that expected currency is displayed properly", expectedCurrency, actualCurrency);
        }

    }

    @When("the user select {string} from country options")
    public void the_user_select_from_country_options(String country) {
        actions.moveToElement(currencyExchange.flagIcon).click().perform();
        currencyExchange.countriesDropdown.click();
        Driver.get().findElement(By.xpath("//a[contains(.,'" + country + "')]")).click();
        waitForPageToLoad(10);
    }

    @Then("rates should be updated according to the currency option")
    public void rates_should_be_updated_according_to_the_currency_option() {
        actions.moveToElement(currencyExchange.USDPayseraRate).perform();
        String lastUSDPayseraRate = currencyExchange.USDPayseraRate.getText();
        assertFalse("Verify that rate is changed based on currency", lastUSDPayseraRate.equals(firstUSDPayseraRate));
        firstUSDPayseraRate = lastUSDPayseraRate;
    }

    @Then("the loss amount for the currency should be displayed properly")
    public void the_loss_amount_for_the_currency_should_be_displayed_properly() {
        try {
            currencyExchange.acceptCookies.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
            jse.executeScript("window.scrollBy(0,1500)");
            currencyExchange.assertLossAmount();
        }
    }


    @When("the user click on the flag icon at the bottom of the page")
    public void theUserClickOnTheFlagIconAtTheBottomOfThePage() {
        currencyExchange.flagIcon.click();
    }

    @And("the user click on the {string} box")
    public void theUserClickOnTheBox(String boxName) {
        Driver.get().findElement(By.id(boxName)).click();
    }


    @Then("all provided options should be displayed in the {string}")
    public void allProvidedOptionsShouldBeDisplayedInThe(String dropdown) {
        List<WebElement> list = Driver.get().findElements(By.xpath("//ul[@aria-labelledby='" + dropdown + "']/li"));
        List<String> actualOptions = getElementsText(list);

        List<String> expectedOptions = new ArrayList<>();
        switch (dropdown) {
            case "countries-dropdown":
                expectedOptions = currencyExchange.getExpectedCountries();
                break;
            case "languages-dropdown":
                expectedOptions = currencyExchange.getExpectedLanguages();
                break;
        }
        assertEquals(expectedOptions, actualOptions);
    }

    @And("the user click on the box of currency options for {string}")
    public void theUserClickOnTheBoxOfCurrencyOptionsFor(String boxName) {
        Driver.get().findElement(By.xpath("//label[.='" + boxName + "']/..//span[@tabindex='-1']")).click();
    }

    @And("the user select {string} from the currency  list")
    public void theUserSelectFromTheCurrencyList(String currency) {
        Driver.get().findElement(By.xpath("//span[@data-ng-bind='currency'][.='" + currency + "']")).click();
    }

    @Then("displayed currency should be the same with {string}")
    public void displayedCurrencyShouldBeTheSameWith(String expectedCurrency) {
        String actualCurrency = currencyExchange.filteredCurrencyResult.getText();
        assertTrue("Verify that result is filtered accordingly", actualCurrency.contains(expectedCurrency));
    }

    @And("the user click on the Filter button")
    public void theUserClickOnTheFilterButton() {
        currencyExchange.filterButton.click();
        waitFor(2);
    }
}
