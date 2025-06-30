package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    // Locators
    private By nameInput = By.xpath("//input[@data-qa='signup-name']");
    private By emailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By signupErrorMessage = By.xpath("//p[contains(text(),'Email Address already exist!')]");
    private By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By loginErrorMessage = By.xpath("//p[contains(text(),'incorrect')]");

    // Signup Metotları
    public void enterName(String name) {
        WebElement nameElement = wait.until(ExpectedConditions.presenceOfElementLocated(nameInput));
        js.executeScript("arguments[0].scrollIntoView(true);", nameElement);
        nameElement.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.presenceOfElementLocated(emailInput));
        js.executeScript("arguments[0].scrollIntoView(true);", emailElement);
        emailElement.sendKeys(email);
    }

    public void clickSignup() {
        WebElement signupBtnElement = wait.until(ExpectedConditions.elementToBeClickable(signupButton));
        js.executeScript("arguments[0].scrollIntoView(true);", signupBtnElement);
        signupBtnElement.click(); // <-- DEĞİŞİKLİK BURADA
    }

    // Login Metotları
    public void loginToAccount(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailInput)).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    // Hata Mesajı Metotları
    public String getLoginErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage)).getText();
    }

    public String getSignupErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signupErrorMessage)).getText();
    }
}