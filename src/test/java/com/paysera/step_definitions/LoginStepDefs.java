package com.paysera.step_definitions;

import com.paysera.pages.LoginPage;
import com.paysera.utilities.BrowserUtils;
import com.paysera.utilities.ConfigurationReader;
import com.paysera.utilities.Driver;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import org.openqa.selenium.By;

public class LoginStepDefs {
    LoginPage login = new LoginPage();

    @Given("the user is on the Login page;")
    public void theUserIsOnTheLoginPage() {
        Driver.get().get(ConfigurationReader.get("LoginPage"));
    }

    @When("the user enter {string}  into the email input box")
    public void the_user_enter_into_the_email_input_box(String email) {
        login.userIdentifierBox.sendKeys(email);
    }

    @When("the user click on the {string} button")
    public void the_user_click_on_the_button(String buttonName) {
        Driver.get().findElement(By.xpath("//button[.='" + buttonName + "']")).click();

    }

    @When("the user enter {string}  into the password input box")
    public void the_user_enter_into_the_password_input_box(String password) {
        login.passwordBox.sendKeys(password);
    }

    @Then("the user should navigate to {string} page")
    public void the_user_should_navigate_to_page(String expectedPage) {
        BrowserUtils.waitFor(5);
        String actualPage = Driver.get().getTitle();
        assertEquals("Verify that user is on the correct page",expectedPage,actualPage);
    }

    @Then("the displayed email address on the account should be {string}")
    public void the_displayed_email_address_on_the_account_should_be(String expectedEmail) {
       try {
           login.dontAgainbutton.click();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           String actualEmail=login.userTitle.getText();
           assertEquals("Verify that correct email is displayed",expectedEmail,actualEmail);
       }
    }


    @And("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String expectedMessage) {
        String actualMessage = login.errorAlert.getText();
        assertEquals("Verify that correct message is displayed",expectedMessage,actualMessage);
    }


}
