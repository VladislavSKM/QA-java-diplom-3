import io.qameta.allure.Description;
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
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        userSteps = new UserSteps(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Check buns chapter (successful)")
    public void checkBunsChapterTest() {
        // Создаем данные пользователя для теста
        user = User.createRandom();
        ValidatableResponse response = userSteps.create(user);
        accessToken = response.extract().path("accessToken");
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
        constructor.isBunsHeaderVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);

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
        constructor.clickConstructorButton();
        constructor.isBunsHeaderVisible();
    }

    @Test
    @Description("Check sauce chapter (successful)")
    public void checkSauceChapterTest() {
        // Создаем данные пользователя для теста
        user = User.createRandom();
        ValidatableResponse response = userSteps.create(user);
        accessToken = response.extract().path("accessToken");
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
        constructor.clickSauceButton();
        constructor.isSauceHeaderVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
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
        constructor.clickSauceButton();
        constructor.isSauceHeaderVisible();
    }

    @Test
    @Description("Check fillings chapter (successful)")
    public void checkSauceFillingsTest() {
        // Создаем данные пользователя для теста
        user = User.createRandom();
        ValidatableResponse response = userSteps.create(user);
        accessToken = response.extract().path("accessToken");
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
        constructor.clickFillingsButton();
        constructor.isFillingsHeaderVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
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
        constructor.clickFillingsButton();
        constructor.isFillingsHeaderVisible();
    }

}
