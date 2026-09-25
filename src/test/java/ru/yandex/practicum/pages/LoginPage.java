package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath("//input[@type='text' and @name='name']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }
}