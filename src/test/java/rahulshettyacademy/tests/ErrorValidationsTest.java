package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.RetryAnalyzer;

public class
ErrorValidationsTest extends BaseTest {

    @Test(groups = "ErrorHandling", retryAnalyzer = RetryAnalyzer.class)
    public void loginErrorValidation() {
        loginPage.loginApplication("stojan@gmail.com", "errorpassword");
        Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "zara coat 3";

        ProductCatalogPage productCatalogPage = loginPage.loginApplication("stojan@gmail.com", "Stojan2024.");

        productCatalogPage.addToCart(productName);
        CartPage cartPage = productCatalogPage.goToCartPage();

        Assert.assertFalse(cartPage.verifyProductDisplay("zara coat 33"));
    }
}
