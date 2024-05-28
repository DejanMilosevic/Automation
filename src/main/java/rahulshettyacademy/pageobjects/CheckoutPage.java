package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    WebElement placeOrder;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String country) {
        selectCountry.sendKeys(country);
        waitForElementToBeVisibleOnPage(results);
        By countryBy = By.xpath("//*[text()=' " + country + "']");
        driver.findElement(countryBy).click();
    }

    public ConfirmationPage placeOrder() {
        placeOrder.click();
        return new ConfirmationPage(driver);
    }
}
