package com.paysera.pages;

import com.paysera.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "span.js-localization-popover")
    public WebElement flagIcon;

    @FindBy(id = "countries-dropdown")
    public WebElement countriesDropdown;

    @FindBy(xpath = "//button[.='Accept recommended settings']")
    public WebElement acceptCookies;









}
