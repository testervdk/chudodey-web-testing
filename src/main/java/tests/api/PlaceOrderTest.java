package tests.api;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.APIServices;

public class PlaceOrderTest {
    private APIServices authService;
    private String expectedUrl = "https://chudodey.com/accounts/order_login";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        authService = new APIServices();
    }

    @Test
    public void placeOrderWithoutAuthAndRedirect() {
        Response response = (authService.placeOrderWithoutRedirectResponse());

        Headers headers = response.getHeaders();

        String locationHeader = headers.getValue("Location");

        Assert.assertEquals(302, response.getStatusCode());
        Assert.assertEquals(locationHeader, expectedUrl);
    }
}