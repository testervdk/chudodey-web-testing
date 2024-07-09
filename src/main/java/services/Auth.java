package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Auth {
    private static final String BASE_URL = "https://chudodey.com";

    public Auth() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response authorizationResponse(String phone, String password) {
        return given()
                .formParam("AccountsDate[telephone]", phone)
                .formParam("AccountsDate[password]", password)
                .when()
                .post("/accounts/authorization");
    }
}