package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

import java.util.List;

public class ProductCatalogPage extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector("button.w-10");
    By toastContainer = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ng-tns-c31-0");

    public List<WebElement> getProductsList() {
        waitForElementToBeVisibleOnPage(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductsList().stream()
                .filter(p -> p.findElement(By.cssSelector("h5")).getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
    }

    public void addToCart(String productName) throws InterruptedException {
        WebElement product = getProductByName(productName);
        if (productName != null) {
            product.findElement(addToCart).click();
            waitForElementToBeVisibleOnPage(toastContainer);
            waitForElementToBeInvisibleOnPage(spinner);
        }
    }
}
