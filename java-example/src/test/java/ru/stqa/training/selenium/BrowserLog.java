package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

import java.util.List;

/**
 * Created by Alavit on 28.01.2017.
 */
public class BrowserLog {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void AreAnyMessagesInBrowserLog() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> products = driver.findElements(By.xpath("//tbody/tr//a[contains(.,'Duck') and not(contains(.,'Rubber'))]"));

        for (int i = 0; i < products.size() ; i++) {
            products.get(i).click();
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            assertTrue(logs.size() == 0);
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            products = driver.findElements(By.xpath("//tbody/tr//a[contains(.,'Duck') and not(contains(.,'Rubber'))]"));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
