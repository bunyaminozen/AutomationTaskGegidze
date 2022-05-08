package com.paysera.pages;

import com.paysera.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class CurrencyExchangePage extends BasePage{
    @FindBy(xpath = "//label[.='Sell']/../input")
    public WebElement sellAmountInputBox;

    @FindBy(xpath = "//label[.='Buy']/../input")
    public WebElement buyAmountInputBox;

    @FindBy(xpath = "//label[.='Sell']/..//span[@tabindex='-1']")
    public WebElement sellCurrencyOption;

    @FindBy(xpath = "//label[.='Buy']/..//span[@data-ng-bind='$select.selected']")
    public WebElement buyCurrencyOption;

    @FindBy(xpath = "//tr[contains(.,'USD')]/td[3]")
    public WebElement USDPayseraRate;

    @FindBy(xpath = "//th")
    public List<WebElement> columns;

    @FindBy(xpath = "//tr[@class='ng-scope']")
    public List<WebElement> rowsOfCurrency;

    @FindBy(xpath = "//button[contains(.,'Filter')]")
    public WebElement filterButton;

    @FindBy(xpath = "//td[1]")
    public WebElement filteredCurrencyResult;

    public WebElement getInputBox(String boxName){
        WebElement inputBox = Driver.get().findElement(By.xpath("//label[.='" + boxName + "']/../input"));
        return inputBox;
    }

    public void assertLossAmount(){
        for (int i = 1; i < rowsOfCurrency.size()+1; i++)  {
            for (int j = 5; j <= columns.size(); j++) {
                String path="(//tr[@class='ng-scope'])["+i+"]/td["+j+"]";

                if(!Driver.get().findElement(By.xpath(path)).getText().equals("-")){

                    //since double data type causes problem during math process,
                    WebElement payseraAmountCell=Driver.get().findElement(By.xpath("(//tr[@class='ng-scope'])["+i+"]/td[4]"));
                    int payseraAmount = (int) (Double.valueOf(payseraAmountCell.getText().replaceAll(",",""))*100);
                    System.out.println("payseraAmount = " + payseraAmount);

                    WebElement bankAmountCell=Driver.get().findElement(By.xpath(path+"//span[@class='ng-binding']"));
                    int bankAmount= (int) (Double.valueOf(bankAmountCell.getText().replaceAll(",",""))*100);
                    System.out.println("bankAmount = " + bankAmount);

                    WebElement lossAmountCell=Driver.get().findElement(By.xpath(path+"//span[2]"));
                    String lossAmountText=lossAmountCell.getText();
                    int lossAmount= (int) (Double.valueOf(lossAmountText.substring(2,lossAmountText.length()-1))*100);
                    System.out.println("lossAmount = " + lossAmount);

                    Assert.assertTrue(i+".row "+j+".column failed",payseraAmount-bankAmount==lossAmount);
                }
            }
        }
    }

    //since there is no documentation, an expected country list was created for assertion
    public List<String> getExpectedCountries() {
        String[] countries = {"Lithuania", "Latvia", "Estonia", "Bulgaria",
                "Spain","Romania", "Poland", "United Kingdom",
                "Germany", "Russia", "Algeria", "Albania",
                "Kosovo", "Ukraine", "France", "Another country"};
        List<String> expectedCountries = Arrays.asList(countries);
        return expectedCountries;
    }
    //since there is no documentation, an expected language list was created for assertion
    public List<String> getExpectedLanguages() {
        String[] languages = {"Lietuvių", "English", "Русский", "Polski", "Latviešu", "Eesti", "Български",
                "Español", "Română", "Deutsch", "Shqip", "Shqip (Kosovë)", "Українська", "Français"};
        List<String> expectedLanguages = Arrays.asList(languages);
        return expectedLanguages;
    }
    //since there is no documentation, an expected currency list was created for assertion
    public List<String> getExpectedCurrencies() {
        String[] currencies = {"EUR","USD","RUB","DKK","PLN","NOK","GBP","SEK","CZK","AUD",
                "CHF","JPY","CAD","HUF","RON","BGN","GEL","TRY","HRK","CNY","KZT","NZD",
                "HKD","INR","ILS","MXN","ZAR","RSD","SGD","PHP","BYN","THB","ALL"};
        List<String> expectedCurrencies = Arrays.asList(currencies);
        return expectedCurrencies;
    }
}
