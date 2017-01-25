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

import static org.junit.Assert.assertTrue;

/**
 * Created by Alavit on 25.01.2017.
 */
public class AddNewProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void AddNewProductAdmin() throws InterruptedException {
        //Login at admin section
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //Catalog - Add New Product
        driver.findElement(By.cssSelector("ul[id = box-apps-menu] > li > a[href *= catalog]")).click();

        //Getting amount of products BEFORE adding new product
        String text1 = driver.findElement(By.xpath("//table[@class='dataTable']//td[contains(text(),'Products')]")).getAttribute("textContent");
        int semicolonIndex1 = text1.lastIndexOf(":");
        int productAmount1 = Integer.parseInt(text1.substring(semicolonIndex1+2,text1.length()));

        driver.findElement(By.cssSelector("a.button[href *= edit_product]")).click();

        //General tab
        driver.findElement(By.xpath("//label[text()=' Enabled']")).click();
        driver.findElement(By.xpath("//input[@type='text'][@name='name[en]']")).sendKeys("Steel Duck");
        driver.findElement(By.xpath("//tr/td/input[contains(@type,'checkbox') and contains(@data-name,'Rubber Ducks')]")).click();
        WebElement select1 = driver.findElement(By.cssSelector("select[name = default_category_id]"));
        Select defCategorySelect = new Select(select1);
        defCategorySelect.selectByIndex(1);
        driver.findElement(By.xpath("//input[@type='checkbox' and @name='product_groups[]' and @value='1-3']")).click();
        WebElement quantity = driver.findElement(By.cssSelector("input[type=number][name=quantity]"));
        quantity.clear();
        quantity.sendKeys("5");
        driver.findElement(By.xpath("//input[@type='file' and @name='new_images[]']")).sendKeys("D:\\steel_duck.jpg");

        //Information tab
        driver.findElement(By.cssSelector("ul.index li a[href *= information]")).click();
        WebElement select2 = driver.findElement(By.cssSelector("select[name = manufacturer_id]"));
        Select manufacturerSelect = new Select(select2);
        manufacturerSelect.selectByIndex(1);
        driver.findElement(By.cssSelector("input[type = text][name = keywords]")).sendKeys("steel duck");
        driver.findElement(By.xpath("//input[@type='text'][@name='short_description[en]']")).sendKeys("Steel Duck is not like Rubber Duck");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("This toy can be a gift to a child or an adult for a special ocassion");
        driver.findElement(By.xpath("//input[@type='text' and @name='head_title[en]']")).sendKeys("Steel Duck");

        //Prices tab
        driver.findElement(By.cssSelector("ul.index li a[href *= prices]")).click();
        WebElement purchasePrice = driver.findElement(By.cssSelector("input[type=number][name=purchase_price]"));
        purchasePrice.clear();
        purchasePrice.sendKeys("20");
        WebElement select3 = driver.findElement(By.cssSelector("select[name = purchase_price_currency_code]"));
        Select currencySelect = new Select(select3);
        currencySelect.selectByIndex(1);
        WebElement price = driver.findElement(By.xpath("//input[@type='text' and @name='prices[USD]']"));
        price.clear();
        price.sendKeys("25");

        //Save all changes
        driver.findElement(By.xpath("//button[@type='submit' and @name='save']")).click();

        //Getting amount of products AFTER adding new product
        String text2 = driver.findElement(By.xpath("//table[@class='dataTable']//td[contains(text(),'Products')]")).getAttribute("textContent");
        int semicolonIndex2 = text2.lastIndexOf(":");
        int productAmount2 = Integer.parseInt(text2.substring(semicolonIndex2+2,text2.length()));

        //Assert
        assertTrue(productAmount2 == productAmount1 + 1);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
