package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By signupLoginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    By cartLink = By.xpath("//a[@href='/view_cart']");
    By logoutLink = By.xpath("//a[contains(text(),'Logout')]");

    public void clickSignupLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
    }

    public void goToCart() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(cartLink));
    }

    public void clickLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}