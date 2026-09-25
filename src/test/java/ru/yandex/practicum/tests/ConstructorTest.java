package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.pages.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void switchToSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorTab("Соусы");
        Assert.assertTrue(mainPage.isTabActive("Соусы"));
        Assert.assertFalse(mainPage.isTabActive("Булки"));
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void switchToFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorTab("Начинки");
        Assert.assertTrue(mainPage.isTabActive("Начинки"));
        Assert.assertFalse(mainPage.isTabActive("Булки"));
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void switchToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorTab("Соусы");
        Assert.assertTrue(mainPage.isTabActive("Соусы"));
        mainPage.clickConstructorTab("Булки");
        Assert.assertTrue(mainPage.isTabActive("Булки"));
        Assert.assertFalse(mainPage.isTabActive("Соусы"));
    }
}