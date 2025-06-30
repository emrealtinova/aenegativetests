
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountInfoPage {
    WebDriver driver;
    JavascriptExecutor js; 

    public AccountInfoPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    By selectTitleMr = By.id("id_gender1");
    By passwordButton = By.id("password");
    By daysDropdown = By.id("days");
    By monthsDropdown = By.id("months");
    By yearsDropDown = By.id("years");
    By newsletterCheckbox = By.id("newsletter");
    By offersCheckbox = By.id("optin");
    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By addressInput = By.id("address1");
    By countryDropdown = By.id("country");
    By stateInput = By.id("state");
    By cityInput = By.id("city");
    By zipInput = By.id("zipcode");
    By mobileNumberInput = By.id("mobile_number");
    By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    By endPageButton = By.xpath("//a[@data-qa='continue-button']");


    public void selectTitleMr() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(selectTitleMr))
                .click();
    }

    public void enterPassword(String password) {
        driver.findElement(passwordButton).sendKeys(password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        new Select(driver.findElement(daysDropdown)).selectByValue(day);
        new Select(driver.findElement(monthsDropdown)).selectByValue(month);
        new Select(driver.findElement(yearsDropDown)).selectByValue(year);
    }


    public void checkNewsletter() {
        WebElement newsletter = driver.findElement(newsletterCheckbox);
        js.executeScript("arguments[0].click();", newsletter);
    }

    public void checkSpecialOffers() {
        WebElement offers = driver.findElement(offersCheckbox);
        js.executeScript("arguments[0].click();", offers);
    }

    public void fillAddressInfo(String firstName, String lastName, String address, String country, String state,
            String city, String zip, String mobile) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipInput).sendKeys(zip);
        driver.findElement(mobileNumberInput).sendKeys(mobile);
    }

    public void clickCreateAccount() {
        WebElement createButton = driver.findElement(createAccountButton);
        js.executeScript("arguments[0].scrollIntoView(true);", createButton);
        js.executeScript("arguments[0].click();", createButton);
    }

    public void clickendPageButton() {
        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(endPageButton));
        continueButton.click();
    }
}