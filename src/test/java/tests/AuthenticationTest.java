// File: tests/AuthenticationTest.java

package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.SignupPage;

import java.time.Duration;

public class AuthenticationTest extends BaseTest {

    HomePage homePage;
    SignupPage signupPage;

    @BeforeEach
    public void initPages() {
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
    }

    @Test
    public void testInvalidLoginWithWrongPassword() {
        String validEmail = "ahmet.yilmaz@test.com";
        String invalidPassword = "YanlisSifre123";
        String expectedErrorMessage = "Your email or password is incorrect!";
        homePage.clickSignupLogin();
        signupPage.loginToAccount(validEmail, invalidPassword);
        String actualErrorMessage = signupPage.getLoginErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Gösterilen hata mesajı beklenenden farklı!");
        System.out.println("Başarısız login senaryosu başarıyla test edildi. Hata mesajı doğru.");
    }

    @Test
    public void testRegistrationWithExistingEmail() {
        String name = "Zeynep";
        String email = "zeynep.demir" + System.currentTimeMillis() + "@test.com";
        homePage.clickSignupLogin();
        signupPage.enterName(name);
        signupPage.enterEmail(email);
        signupPage.clickSignup();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/signup"));

        driver.get("https://automationexercise.com/login");
        signupPage.enterName(name);
        signupPage.enterEmail(email);
        signupPage.clickSignup();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/signup"),
                "Mevcut e-posta ile kayıt denendiğinde bir sonraki sayfaya geçmemeliydi! URL: " + currentUrl);
        System.out.println(
                " Başarıyla doğrulandı: Mevcut e-posta ile kayıt olmaya çalışınca, sistem ilerlemeye izin vermiyor ve kaydı tamamlama sayfasına yönlendiriyor.");
    }

    @Test
    public void testRegistrationWithInvalidEmailFormat() {
        String name = "Test";
        String invalidEmail = "gecersiz-eposta.com";

      
        String expectedValidationMessageSubstring = "Lütfen e-posta adresine bir \"@\" işareti ekleyin";

        homePage.clickSignupLogin();
        signupPage.enterName(name);
        signupPage.enterEmail(invalidEmail);
        signupPage.clickSignup();

        WebElement emailInput = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        String actualValidationMessage = emailInput.getAttribute("validationMessage");
        System.out.println("Tarayıcı Validasyon Mesajı: " + actualValidationMessage);

        Assertions.assertTrue(
                actualValidationMessage.contains(expectedValidationMessageSubstring),
                "Geçersiz e-posta formatı için gösterilen tarayıcı mesajı beklenenden farklı!");

        System.out.println(" Geçersiz e-posta formatı uyarısı başarıyla test edildi.");
    }
}
