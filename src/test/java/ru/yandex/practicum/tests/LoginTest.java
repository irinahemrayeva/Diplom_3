package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.pages.ForgotPasswordPage;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegisterPage;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void loginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage afterLogin = new MainPage(driver);
        Assert.assertTrue(afterLogin.isPlaceOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage afterLogin = new MainPage(driver);
        Assert.assertTrue(afterLogin.isPlaceOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegisterPageLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        LoginPage afterRedirect = new LoginPage(driver);
        afterRedirect.login(user.getEmail(), user.getPassword());

        MainPage afterLogin = new MainPage(driver);
        Assert.assertTrue(afterLogin.isPlaceOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPageLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        LoginPage afterRedirect = new LoginPage(driver);
        afterRedirect.login(user.getEmail(), user.getPassword());

        MainPage afterLogin = new MainPage(driver);
        Assert.assertTrue(afterLogin.isPlaceOrderButtonVisible());
    }
}