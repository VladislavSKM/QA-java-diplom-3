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
import pageObject.pages.PrivateOfficePage;
import pageObject.user.User;
import pageObject.user.UserSteps;
import static pageObject.user.UserConstants.REQUEST_SPECIFICATION;
import static pageObject.user.UserConstants.RESPONSE_SPECIFICATION;

public class PrivateOfficeTest {
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
    @Description("Check private office page (successful)")
    public void checkPrivateOfficePageTest() {
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
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.isCreateOrderButtonVisible();
        mainBurgerPage.clickLoginToPrivateOfficeButton();
        // (Форма 3) Страница "Личный кабинет"
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        privateOfficePage.isProfileHeaderVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

    @Test
    @Description("Check transition to constructor page (successful)")
    public void checkTransitionToConstructorPageTest() {
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
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.isCreateOrderButtonVisible();
        mainBurgerPage.clickLoginToPrivateOfficeButton();
        // (Форма 3) Страница "Личный кабинет"
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        privateOfficePage.isProfileHeaderVisible();
        privateOfficePage.clickConstructorButton();
        // (Форма 4) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

    @Test
    @Description("Check transition to Stellar-burger page (successful)")
    public void checkTransitionToStellarBurgerPageTest() {
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
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.isCreateOrderButtonVisible();
        mainBurgerPage.clickLoginToPrivateOfficeButton();
        // (Форма 3) Страница "Личный кабинет"
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        privateOfficePage.isProfileHeaderVisible();
        privateOfficePage.clickStellarBurgerButton();
        // (Форма 4) Страница "Конструктор"
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor.isCreateBurgerHeaderVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }
}