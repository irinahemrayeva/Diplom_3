package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.xpath("//input[@type='text' and @name='name']");
    private final By emailInput = By.xpath("(//input[@type='text'])[2]");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By pageTitle = By.xpath("//h2[text()='Регистрация']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Зарегистрировать пользователя: name={name}, email={email}")
    public void register(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).clear();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(pageTitle).click();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    @Step("Проверить, показана ли ошибка 'Некорректный пароль'")
    public boolean isPasswordErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }

    @Step("Кликнуть ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}