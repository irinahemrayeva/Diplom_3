package ru.yandex.practicum.pages;

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

    // Имя — первое поле type="text" в форме регистрации
    private final By nameInput = By.xpath("//input[@type='text' and @name='name']");
    // Email — второе поле type="text"
    private final By emailInput = By.xpath("(//input[@type='text'])[2]");
    // Пароль
    private final By passwordInput = By.xpath("//input[@type='password']");
    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    // Ошибка "Некорректный пароль"
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");
    // Ссылка "Войти"
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By pageTitle = By.xpath("//h2[text()='Регистрация']");

    public void register(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).clear();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        // Принудительно снимаем фокус с поля пароля — триггерим валидацию
        driver.findElement(pageTitle).click();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public boolean isPasswordErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}