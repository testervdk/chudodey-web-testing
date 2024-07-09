package tests.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.Auth;

public class AuthorizationTest {
    private Auth authService;

    String userPhoneNumber = "+7 000 000 00 00";
    String userPassword = "123456";
    String expectedErrorMessage = "Неверная комбинация логина и пароля";

    @BeforeTest
    public void setUp() {
        authService = new Auth();
    }


    @Test
    public void invalidAuthorization() {
        Response response = (authService.authorizationResponse(userPhoneNumber, userPassword));

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(authService.isUserDataWrong(userPhoneNumber, userPassword, expectedErrorMessage));
    }
}