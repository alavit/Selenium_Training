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
        String priceRegular1FontSize = block.findElement(By.xpath(".//div[@class='price-wrapper']/s[@class='regular-price']")).getCssValue("font-size");
        String priceRegular1CrossedOut = block.findElement(By.xpath(".//div[@class='price-wrapper']/s[@class='regular-price']")).getCssValue("text-decoration");

        String priceCampaign1 = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getAttribute("textContent");
        String priceCampaign1Color = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color");
        String priceCampaign1FontSize = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
        String priceCampaign1FontWeight = block.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");

        //Click on product page, get its name, prices and styles of prices
        driver.findElement(By.xpath("//div[contains(@id,'campaigns')]//a[@class='link']")).click();

        String name2 = driver.findElement(By.xpath("//h1[@class='title']")).getAttribute("textContent");

        String priceRegular2 = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getAttribute("textContent");
        String priceRegular2Color = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getCssValue("color");
        String priceRegular2FontSize = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getCssValue("font-size");
        String priceRegular2CrossedOut = driver.findElement(By.xpath("//div[@id='box-product']//s[@class='regular-price']")).getCssValue("text-decoration");

        String priceCampaign2 = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getAttribute("textContent");
        String priceCampaign2Color = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color");
        String priceCampaign2FontSize = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
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

        assertTrue(name1.equals(name2)
                                && priceRegular1.equals(priceRegular2)
                                && priceCampaign1.equals(priceCampaign2)
                                && priceRegular1Color.equals(priceRegular2Color)
                                && priceRegular1FontSize.equals(priceRegular2FontSize)
                                && priceRegular1CrossedOut.equals(priceRegular2CrossedOut)
                                && priceCampaign1Color.equals(priceCampaign2Color)
                                && priceCampaign1FontSize.equals(priceCampaign2FontSize)
                                && priceCampaign1FontWeight.equals(priceCampaign2FontWeight)
        );
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
