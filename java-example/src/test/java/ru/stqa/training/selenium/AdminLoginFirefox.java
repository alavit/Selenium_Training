package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class AdminLoginFirefox {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        //запуск Firefox ESR 45 по старой схеме
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(FirefoxDriver.MARIONETTE, false);
//        driver = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR45\\firefox.exe")),
//                new FirefoxProfile(),caps);
//        System.out.println(((HasCapabilities)driver).getCapabilities());


       //запуск Firefox по новой схеме
        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void adminLogin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
