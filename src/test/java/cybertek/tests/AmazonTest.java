package cybertek.tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import cybertek.TestBase;
import cybertek.pages.CartPage;
import cybertek.utilities.ConfigurationReader;
import cybertek.utilities.Driver;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AmazonTest extends TestBase {


    @Test
    public void test1() throws InterruptedException {

        double expectedTotalPrice = 0.0;

        String product = ConfigurationReader.get("product");
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys(product, Keys.ENTER);

        //clicking on first result
        driver.findElement(By.tagName("h2")).click();

        //getting the price of product

        WebElement priceTag = driver.findElement(By.id("price_inside_buybox"));
        double productPrice = Double.parseDouble(priceTag.getText().substring(1));

        //creating a cartPage 
        CartPage cartPage = new CartPage();

        //changing the quantity to 2 and adding the product to cart

        selectingQuantity(cartPage.dropdown, 1);
        driver.findElement(By.id("add-to-cart-button")).click();

        //switching to cart page
        driver.findElement(By.id("nav-cart")).click();

        //verifying the quantity
        int expectedValue = 2;
        int actualValue = value(cartPage.quantity);

        if(actualValue==expectedValue){
            expectedTotalPrice = actualValue * productPrice;
        }

        //verifying the expected price

        double actualTotalPrice = priceAsDouble(cartPage.totalPrice);

        Assert.assertEquals(actualTotalPrice,expectedTotalPrice);

        //changing quantity to 1 and verifying quantity and totalPrice changed
        selectingQuantity(cartPage.dropdown, 1);
        Assert.assertEquals(value(cartPage.quantity), 1);

        if(value(cartPage.quantity)==1){
            expectedTotalPrice -= productPrice;
        }

        Thread.sleep(1000);

        actualTotalPrice = priceAsDouble(cartPage.totalPrice);

        Assert.assertEquals(actualTotalPrice,expectedTotalPrice);




    }

    public int value(WebElement quantity){
        return Integer.parseInt(quantity.getText());
    }


    public void selectingQuantity(WebElement element, int index){
        Select priceDropDown = new Select(element);

        priceDropDown.selectByIndex(index);
    }

    public double priceAsDouble(WebElement totalPrice){
        return Double.parseDouble(totalPrice.getText().substring(1));
    }
}
