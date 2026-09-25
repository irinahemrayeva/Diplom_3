package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.models.User;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegisterPage;
import ru.yandex.practicum.utils.UserGenerator;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    public void registerNewUserSuccess() {
        User newUser = UserGenerator.randomUser();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(newUser.getName(), newUser.getEmail(), newUser.getPassword());

        // Ждём редирект на /login
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));

        // Удаляем пользователя через API: логин → токен → delete
        Response loginResponse = userClient.login(newUser);
        String newToken = loginResponse.then().extract().path("accessToken");
        if (newToken != null) {
            userClient.delete(newToken);
        }
    }

    @Test
    @DisplayName("Ошибка при пароле короче 6 символов")
    public void registerWithShortPasswordShowsError() {
        User shortPassUser = new User(
                "test_" + System.currentTimeMillis() + "@yandex.ru",
                "12345",
                "ShortPass"
        );

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(shortPassUser.getName(), shortPassUser.getEmail(), shortPassUser.getPassword());

        Assert.assertTrue("Должна быть ошибка 'Некорректный пароль'",
                registerPage.isPasswordErrorVisible());
    }
}