package rahulshettyacademy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.LoginPage;

import java.time.Duration;
import java.util.List;

public class StandAloneTest{


    public static void main(String[] args) {

        String productName = "zara coat 3";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("stojan@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Stojan2024.");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement zaraCoat = products.stream()
                .filter(product -> product.findElement(By.cssSelector("h5")).getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);

        if (zaraCoat != null) {
            zaraCoat.findElement(By.cssSelector("button.w-10")).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-tns-c31-0")));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartWrap h3")));
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartWrap h3"));
        boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        driver.findElement(By.xpath("//button[text()='Checkout']")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' India']")));
        a.click(driver.findElement(By.xpath("//*[text()=' India']"))).build().perform();
        a.click(driver.findElement(By.xpath("//a[text()='Place Order ']"))).build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".content-wrap")));

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        driver.close();

    }
}
