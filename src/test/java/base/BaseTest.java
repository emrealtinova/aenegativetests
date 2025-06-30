
package base;

import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.BeforeEach; 
import org.openqa.selenium.WebDriver;
import utils.Driver;

public abstract class BaseTest {
    protected WebDriver driver; 

    @BeforeEach 
    public void setUp() {
        driver = Driver.getDriver();
        driver.get("https://automationexercise.com");
    }

    @AfterEach 
    public void tearDown() {
        Driver.quitDriver();
    }
}