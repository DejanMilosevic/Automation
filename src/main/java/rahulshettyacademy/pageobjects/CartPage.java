package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProductTitles;
    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkout;

    By cartProductsBy = By.cssSelector(".cartSection h3");

    public List<WebElement> getCartProductTitles() {
        waitForElementToBeVisibleOnPage(cartProductsBy);
        return cartProductTitles;
    }

    public boolean verifyProductDisplay(String productName) {
        return getCartProductTitles().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckoutPage() {
        checkout.click();
        return new CheckoutPage(driver);
    }
}
