package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alavit on 22.01.2017.
 */
public class CheckStickers {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void CheckProductSticker() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        List <WebElement> wrappers = driver.findElements(By.cssSelector("a.link > div.image-wrapper"));
        for (int i = 0; i < wrappers.size(); i++) {
            assertTrue(wrappers.get(i).findElements(By.cssSelector("div[class *= sticker]")).size() == 1);
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
