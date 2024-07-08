package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static io.restassured.RestAssured.given;

public class Auth {
    private static final String BASE_URI = "https://chudodey.com";

    public Auth() {
        RestAssured.baseURI = BASE_URI;
    }

    public Response authorizationResponse(String phone, String password) {
        return given()
                .contentType("application/json")
                .formParam("telephone", phone)
                .formParam("password", password)
                .when()
                .post("/accounts/login")
                .then()
                .extract().response();
    }

    public String contentType(String phone, String password) {
        return authorizationResponse(phone, password).contentType();
    }

    public boolean isUserDataWrong(String phone, String password, String message) {
        if(contentType(phone, password).contains("text/html")) {
            String htmlBody = authorizationResponse(phone, password).getBody().asString();

            Document doc = Jsoup.parse(htmlBody);
            Element errorElement = doc.selectFirst("div.bottom-error");

            return errorElement != null && errorElement.text().equals(message);
        }

        return false;
    }
}
