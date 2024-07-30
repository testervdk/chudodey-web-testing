package tests.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.APIServices;

public class RecoveryPasswordAPITest {
    private APIServices recoveryPasswordService;

    private final String USER_PHONE = "+7 000 000 00 00";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        recoveryPasswordService = new APIServices();
    }

    @Test
    public void recoveryPassword() {
        Response response = (recoveryPasswordService.recoveryPasswordResponse(USER_PHONE));

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json", response.getContentType());
    }
}