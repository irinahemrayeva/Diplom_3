package ru.yandex.practicum.clients;

import io.restassured.response.Response;
import ru.yandex.practicum.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public Response create(User user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    public Response delete(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    public Response login(User user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");
    }
}