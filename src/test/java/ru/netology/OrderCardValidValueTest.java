package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderCardValidValueTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void createBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    void orderCardTest() {
        driver.get("http://0.0.0.0:9999");
        WebElement inputName = driver.findElement(By.cssSelector("[data-test-id = 'name'] input"));
        inputName.sendKeys("Иванов Иван");
        WebElement inputPhone = driver.findElement(By.cssSelector("[data-test-id = 'phone'] input"));
        inputPhone.sendKeys("+79163131737");
        WebElement buttonAgreement = driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span"));
        buttonAgreement.click();
        WebElement buttonSubmit = driver.findElement(By.className("button__content"));
        buttonSubmit.click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.strip());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
}

