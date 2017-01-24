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
public class CorrectProductPageDisplayed {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void IsCorrectProductPageDisplayed() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");

        //Find first product from Campaigns, get its name, prices and styles of prices
        WebElement block = driver.findElement(By.xpath("//div[contains(@id,'campaigns')]"));

        String name1 = block.findElement(By.xpath(".//div[@class='name']")).getAttribute("textContent");

        String priceRegular1 = block.findElement(By.xpath(".//div[@class='price-wrapper']/s[@class='regular-price']")).getAttribute("textContent");
        String priceRegular1Color = block.findElement(By.xpath(".//div[@class='price-wrapper']/s[@class='regular-price']")).getCssValue("color");
        String priceRegular1FontSizeStr = block.findElement(By.xpath(".//div[@class='price-wrapper']/s[@class='regular-price']")).getCssValue("font-size").replaceAll("px","");
        double priceRegular1FontSize = Double.parseDouble(priceRegular1FontSizeStr);
        String priceRegular1CrossedOut = block.findElement(By.xpath(".//div[@class='price-wrapper']/s[@class='regular-price']")).getCssValue("text-decoration");

        String priceCampaign1 = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getAttribute("textContent");
        String priceCampaign1Color = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color");
        String priceCampaign1FontSizeStr = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size").replaceAll("px","");
        double priceCampaign1FontSize = Double.parseDouble(priceCampaign1FontSizeStr);
        String priceCampaign1FontWeight = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");

        //Click on product page, get its name, prices and styles of prices
        driver.findElement(By.xpath("//div[contains(@id,'campaigns')]//a[@class='link']")).click();

        String name2 = driver.findElement(By.xpath("//h1[@class='title']")).getAttribute("textContent");

        String priceRegular2 = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getAttribute("textContent");
        String priceRegular2Color = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getCssValue("color");
        String priceRegular2FontSizeStr = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getCssValue("font-size").replaceAll("px","");
        double priceRegular2FontSize = Double.parseDouble(priceRegular2FontSizeStr);
        String priceRegular2CrossedOut = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getCssValue("text-decoration");

        String priceCampaign2 = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getAttribute("textContent");
        String priceCampaign2Color = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color");
        String priceCampaign2FontSizeStr = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size").replaceAll("px","");
        double priceCampaign2FontSize = Double.parseDouble(priceCampaign2FontSizeStr);
        String priceCampaign2FontWeight = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");

/*        System.out.println(priceRegular1 +" "+ priceRegular2);
        System.out.println(priceRegular1Color +" "+ priceRegular2Color);
        System.out.println(priceRegular1FontSize +" "+ priceRegular2FontSize);
        System.out.println(priceRegular1CrossedOut +" "+ priceRegular2CrossedOut);
        System.out.println();
        System.out.println(priceCampaign1 +" "+ priceCampaign2);
        System.out.println(priceCampaign1Color +" "+ priceCampaign2Color);
        System.out.println(priceCampaign1FontSize +" "+ priceCampaign2FontSize);
        System.out.println(priceCampaign1FontWeight +" "+ priceCampaign2FontWeight);*/

        //Global asserts
        assertTrue(name1.equals(name2));
        assertTrue(priceRegular1.equals(priceRegular2));
        assertTrue(priceCampaign1.equals(priceCampaign2));

        //Main page asserts
        assertTrue(priceRegular1Color.equals("rgba(119, 119, 119, 1)"));
        assertTrue(priceCampaign1FontSize > priceRegular1FontSize);
        assertTrue(priceRegular1CrossedOut.equals("line-through"));

        assertTrue(priceCampaign1Color.equals("rgba(204, 0, 0, 1)"));
        assertTrue(priceCampaign1FontWeight.equals("bold"));

        //Product page asserts
        assertTrue(priceRegular2Color.equals("rgba(102, 102, 102, 1)"));
        assertTrue(priceCampaign2FontSize > priceRegular2FontSize);
        assertTrue(priceRegular2CrossedOut.equals("line-through"));

        assertTrue(priceCampaign2Color.equals("rgba(204, 0, 0, 1)"));
        assertTrue(priceCampaign2FontWeight.equals("bold"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
