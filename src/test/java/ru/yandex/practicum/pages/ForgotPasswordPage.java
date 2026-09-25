package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;

    // Ссылка "Войти" на странице восстановления пароля
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}