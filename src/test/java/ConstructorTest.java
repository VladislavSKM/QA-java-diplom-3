import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.pages.AuthUserPage;
import pageObject.pages.ConstructorPage;
import pageObject.pages.MainBurgerPage;
import pageObject.user.User;
import pageObject.user.UserSteps;
import static pageObject.user.UserConstants.REQUEST_SPECIFICATION;
import static pageObject.user.UserConstants.RESPONSE_SPECIFICATION;

public class ConstructorTest {
    private WebDriver driver;
    private UserSteps userSteps;
    private User user;
    private String accessToken;

    @Before
    @Step("Создание драйвера браузера")
    public void createDriver() {
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            browserName = "chrome";
        }

        ChromeOptions options = new ChromeOptions();
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver(options);
                break;

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new RuntimeException("Некорректный браузер: " + browserName);
        }
        driver.manage().window().maximize();
    }

    @Before
    @Step("Создание клинта")
    public void createUser() {
        // Создаем данные пользователя для теста
        userSteps = new UserSteps(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
        user = User.createRandom();
        ValidatableResponse response = userSteps.create(user);
        accessToken = response.extract().path("accessToken");
    }

    @After
    @Step("Удаляем клинта и закрываем браузер")
    public void tearDown() {
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
        driver.quit();
    }

    @Test
    @Description("Check buns chapter (successful)")
    public void checkBunsChapterTest() {
        // (Форма 1) Главная страница Stellarburgers
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        mainBurgerPage.open();
        mainBurgerPage.isLoginToAccountButtonVisible();
        mainBurgerPage.clickLoginToAccountButton();
        // (Форма 2) Страница авторизации
        AuthUserPage authUserPage = new AuthUserPage(driver);
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(user.getEmail());
        authUserPage.sendPassword(user.getPassword());
        authUserPage.clickLoginUserButton();
        // (Форма 3) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        constructor.isBunsButtonVisible();
        constructor.isBunsHeaderVisible();
    }

    @Test
    @Description("Check buns chapter without login (successful)")
    public void checkBunsChapterWithoutLoginTest() {
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.open();
        // (Форма 2) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        constructor.isBunsButtonVisible();
    }

    @Test
    @Description("Check sauce chapter (successful)")
    public void checkSauceChapterTest() {
        // (Форма 1) Главная страница Stellarburgers
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        mainBurgerPage.open();
        mainBurgerPage.isLoginToAccountButtonVisible();
        mainBurgerPage.clickLoginToAccountButton();
        // (Форма 2) Страница авторизации
        AuthUserPage authUserPage = new AuthUserPage(driver);
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(user.getEmail());
        authUserPage.sendPassword(user.getPassword());
        authUserPage.clickLoginUserButton();
        // (Форма 3) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        constructor.scrollToTheSauceHeader();
        constructor.isSauceButtonVisible();
    }

    @Test
    @Description("Check sauce chapter without login (successful)")
    public void checkSauceChapterWithoutLoginTest() {
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.open();
        // (Форма 2) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        constructor.scrollToTheSauceHeader();
        constructor.isSauceButtonVisible();
    }

    @Test
    @Description("Check fillings chapter (successful)")
    public void checkSauceFillingsTest() {
        // (Форма 1) Главная страница Stellarburgers
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        mainBurgerPage.open();
        mainBurgerPage.isLoginToAccountButtonVisible();
        mainBurgerPage.clickLoginToAccountButton();
        // (Форма 2) Страница авторизации
        AuthUserPage authUserPage = new AuthUserPage(driver);
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(user.getEmail());
        authUserPage.sendPassword(user.getPassword());
        authUserPage.clickLoginUserButton();
        // (Форма 3) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        constructor.scrollToTheFillingsHeader();
        constructor.isFillingsButtonVisible();
    }

    @Test
    @Description("Check fillings chapter without login (successful)")
    public void checkSauceFillingsWithoutLoginTest() {
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.open();
        // (Форма 2) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        constructor.scrollToTheFillingsHeader();
        constructor.isFillingsButtonVisible();
    }

}
