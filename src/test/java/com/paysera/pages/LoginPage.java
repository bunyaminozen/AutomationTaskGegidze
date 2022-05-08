package com.paysera.pages;

import com.paysera.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "userIdentifier")
    public WebElement userIdentifierBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[contains(.,'Do not show it again')]")
    public WebElement dontAgainbutton;

    @FindBy(xpath = "//b[@class='ng-binding']")
    public WebElement userTitle;

    @FindBy(css = "div.alert.alert-danger")
    public WebElement errorAlert;




}
