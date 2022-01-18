package cybertek.pages;

import cybertek.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPage {

    public CartPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//select[@name='quantity']")
    public WebElement dropdown;

    @FindBy(xpath = "(//div[@data-name='Subtotals'])[1]/span[2]/span")
    public WebElement totalPrice;

    @FindBy(className = "a-dropdown-prompt")
    public WebElement quantity;







}
