package ru.yandex.practicum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//a[@href='/account']");
    private final By constructorTitle = By.xpath("//h1[text()='Соберите бургер']");
    private final By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
    }

    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    public boolean isConstructorTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorTitle)).isDisplayed();
    }

    public boolean isPlaceOrderButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).isDisplayed();
    }

    // Клик по вкладке: "Булки", "Соусы" или "Начинки"
    public void clickConstructorTab(String tabName) {
        By tab = By.xpath("//span[text()='" + tabName + "']");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(tab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Проверка активной вкладки (класс tab_tab_type_current)
    public boolean isTabActive(String tabName) {
        By tab = By.xpath("//div[contains(@class,'tab_tab_type_current')]//span[text()='" + tabName + "']");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(tab));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}