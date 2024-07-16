package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIServices {
    private static final String BASE_URL = "https://chudodey.com";

    public APIServices() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response authorizationResponse(String phone, String password) {
        return given()
                .formParam("AccountsDate[telephone]", phone)
                .formParam("AccountsDate[password]", password)
                .post("/accounts/authorization");
    }

    public Response recoveryPasswordResponse(String phone) {
        return given()
                .formParam("AccountsDate[telephone]", phone)
                .post("/accounts/get_flash_call_captcha_code/accounts-recovery_password");
    }

    public Response placeOrderWithoutRedirectResponse() {
        return given()
                .redirects().follow(false)
                .get("/async_cart/steps/step2-address-delivery");
    }



}