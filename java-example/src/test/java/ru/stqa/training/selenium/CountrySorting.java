package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by Alavit on 26.01.2017.
 */
public class CountrySorting {
    private WebDriver driver;
    private WebDriverWait wait;

    public List<WebElement> GetListOfWebElements(String xPathSelector){

        //List of items as Web Elements
        List<WebElement> itemsWE = driver.findElements(By.xpath(xPathSelector));

        return itemsWE;
    }

    public boolean CheckItemsOrder(List<WebElement> itemsWE, String attribute){

        List<String> itemsRaw = new ArrayList<>();
        List<String> itemsSort = new ArrayList<>();

        //List of items as strings
        for (WebElement element:itemsWE) {
            String item = element.getAttribute(attribute);
            itemsRaw.add(item);
        }

        itemsSort = itemsRaw;
        Collections.sort(itemsSort);

        return itemsRaw.equals(itemsSort);
    }


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void CountrySort() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countriesWE = GetListOfWebElements("//tr/td/a[not(contains(@title,'Edit'))]");

        //1a - Assert that countries are in alphabetical order
        assertTrue(CheckItemsOrder(countriesWE,"textContent"));

        List<WebElement> zonesAmountWE = GetListOfWebElements("//tr/td[6]");

        List<Integer> zonesAmount = new ArrayList<>();

        for (WebElement element:zonesAmountWE) {
            int zone = Integer.parseInt(element.getAttribute("textContent"));
            zonesAmount.add(zone);
        }

        for (int i = 0; i < zonesAmount.size() ; i++) {
            if (zonesAmount.get(i) > 0 ){
                countriesWE.get(i).click();
                List<WebElement> zonesWE = GetListOfWebElements("//tr/td/input[@type='hidden' and contains(@name,'[name]')]");

                //1b - Assert that zones are in alphabetical order
                assertTrue(CheckItemsOrder(zonesWE,"value"));

                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                countriesWE =  GetListOfWebElements("//tr/td/a[not(contains(@title,'Edit'))]");
            }
        }

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> geozonesWE = GetListOfWebElements("//tr/td/a[not(contains(@title,'Edit'))]");

        for (int i = 0; i < geozonesWE.size(); i++) {
            geozonesWE.get(i).click();
            List<WebElement> zonesWE = GetListOfWebElements("//tr/td/select[contains(@name,'[zone_code]')]/option[@selected='selected']");

            //2 - Assert that zones are in alphabetical order
            assertTrue(CheckItemsOrder(zonesWE,"textContent"));

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            geozonesWE = GetListOfWebElements("//tr/td/a[not(contains(@title,'Edit'))]");

        }




    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
