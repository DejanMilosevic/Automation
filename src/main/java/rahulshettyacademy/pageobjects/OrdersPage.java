package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

import java.util.List;

public class OrdersPage extends AbstractComponent {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> ordersNames;

    @FindBy(css = ".table-responsive")
    WebElement ordersTable;

    public List<WebElement> getOrdersNames() {
        return ordersNames;
    }

    public boolean verifyOrderDisplay(String orderName) throws InterruptedException {
        Thread.sleep(1000);
        return getOrdersNames().stream().anyMatch(order -> order.getText().equalsIgnoreCase(orderName));
    }
}
