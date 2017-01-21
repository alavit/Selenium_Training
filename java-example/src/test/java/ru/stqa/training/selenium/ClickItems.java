package ru.stqa.training.selenium;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alavit on 19.01.2017.
 */
public class ClickItems {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void ClickOnItems() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

            List <WebElement> items = driver.findElements(By.cssSelector("li#app- > a"));
            int itemsAmount = items.size();

            for (int i = 0; i < itemsAmount; i++) {
                WebElement item = driver.findElement(By.xpath("//li['" + i + "' + 1][@id='app-']/a"));
                item.click();
                List <WebElement> subItems = driver.findElements(By.cssSelector("li[id^=doc] > a"));
                int subItemsAmount = subItems.size();
                if (subItemsAmount > 0){
                    for (int j = 0; j < subItemsAmount ; j++) {
                        WebElement subItem = driver.findElement(By.xpath("//li['" + j + "' + 1][contains(@id,'doc')]/a"));
                        subItem.click();
                        assertTrue(driver.findElements(By.cssSelector("h1")).size() == 1);
                    }
                }
                assertTrue(driver.findElements(By.cssSelector("h1")).size() == 1);
            }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
