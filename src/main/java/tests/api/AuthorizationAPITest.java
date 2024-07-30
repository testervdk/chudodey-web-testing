package tests.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.APIServices;

public class AuthorizationAPITest {
    private APIServices authService;

    private final String USER_PHONE = "+7 000 000 00 00";
    private final String USER_PASSWORD = "123456";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        authService = new APIServices();
    }

    @Test
    public void invalidAuthorization() {
        Response response = (authService.authorizationResponse(USER_PHONE, USER_PASSWORD));

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json", response.getContentType());
    }
}