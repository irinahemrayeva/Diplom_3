package ru.yandex.practicum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.practicum.clients.UserClient;
import ru.yandex.practicum.models.User;
import ru.yandex.practicum.utils.ApiConfig;
import ru.yandex.practicum.utils.UserGenerator;

public class BaseTest {

    protected WebDriver driver;
    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    private static final String BASE_URL = ApiConfig.BASE_URL;
    private static final String YANDEX_BROWSER_PATH =
            "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equalsIgnoreCase(browser)) {
            // для Яндекс.Браузера — скачанный yandexdriver.exe
            System.setProperty("webdriver.chrome.driver",
                    "src/test/resources/yandexdriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary(YANDEX_BROWSER_PATH);
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get(BASE_URL);

        userClient = new UserClient();
        user = UserGenerator.randomUser();
        Response response = userClient.create(user);
        accessToken = response.then().extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}