package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Alavit on 25.01.2017.
 */
public class ShopRegistration {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void ShopUserRegister() throws InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_HH_mm_ss");
        Date date = new Date();
        String email = "a.avitsuk+" + dateFormat.format(date) + "@baseride.com";
        String password = "qwerty123";

        //Registration
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//div[@id='box-account-login']//a")).click();
        driver.findElement(By.cssSelector("input[name = firstname]")).sendKeys("Alex");
        driver.findElement(By.cssSelector("input[name = lastname]")).sendKeys("Avitsuk");
        driver.findElement(By.cssSelector("input[name = address1]")).sendKeys("Baker St. 221");
        driver.findElement(By.cssSelector("input[name = postcode]")).sendKeys("W1A 0AX");
        driver.findElement(By.cssSelector("input[name = city]")).sendKeys("London");
        WebElement select = driver.findElement(By.cssSelector("select[name = country_code]"));
        Select countrySelect = new Select(select);
        countrySelect.selectByIndex(223);
        driver.findElement(By.cssSelector("input[name = email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name = phone]")).sendKeys("+449205554433");
        driver.findElement(By.cssSelector("input[name = password]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name = confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type = submit]")).click();

        //Logout after registration and auto-login
        driver.findElement(By.xpath("//div[@id='box-account']//a[@href='http://localhost/litecart/en/logout']")).click();

        //Login again
        driver.findElement(By.xpath("//form[@name='login_form']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath(".//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath(".//button[@name='login']")).click();

        //Logout again
        driver.findElement(By.xpath("//div[@id='box-account']//a[@href='http://localhost/litecart/en/logout']")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
