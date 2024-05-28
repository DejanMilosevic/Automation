package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest{

    String productName = "zara coat 3";

    @Test(dataProvider = "getData",groups = "Purchase")
    public void submitOrderTest(HashMap<String,String> input) throws InterruptedException {

        String countryName = "India";

        ProductCatalogPage productCatalogPage = loginPage.loginApplication(input.get("email"), input.get("password"));

        productCatalogPage.addToCart(input.get("productName"));
        CartPage cartPage = productCatalogPage.goToCartPage();

        Assert.assertTrue(cartPage.verifyProductDisplay(input.get("productName")));
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();

        Assert.assertEquals(confirmationPage.getConfirmMessage(), "THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = "submitOrderTest")
    public void orderHistoryTest() throws InterruptedException {
        ProductCatalogPage productCatalogPage = loginPage.loginApplication("stojan@gmail.com", "Stojan2024.");
        OrdersPage ordersPage = productCatalogPage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getDataJsonToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

//    @DataProvider
//    public Object[][] getData(){
//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","stojan@gmail.com");
//        map.put("password","Stojan2024.");
//        map.put("productName","zara coat 3");
//
//        HashMap<String,String> map2 = new HashMap<>();
//        map2.put("email","shetty@gmail.com");
//        map2.put("password","Iamking@000");
//        map2.put("productName","adidas original");
//
//        return new Object[][]{{map},{map2}};
//    }

//    @DataProvider
//    public Object[][] getData(){
//        return new Object[][]{{"stojan@gmail.com", "Stojan2024.","zara coat 3"},{"shetty@gmail.com","Iamking@000","adidas original"}};
//    }
}