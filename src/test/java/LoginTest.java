import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.pages.*;
import pageObject.user.User;
import pageObject.user.UserSteps;
import static pageObject.user.UserConstants.REQUEST_SPECIFICATION;
import static pageObject.user.UserConstants.RESPONSE_SPECIFICATION;

public class LoginTest {

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
    @Description("Login user from login to account button (successful)")
    public void loginUserFromLoginAccountButtonTest() {
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
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

    @Test
    @Description("Login user from recovery password page (successful)")
    public void loginUserFromRecoveryPasswordPageTest() {
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
        authUserPage.clickRecoveryPasswordButton();
        // (Форма 3) Страница восстановления пароля
        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.isAuthFormVisible();
        recoveryPasswordPage.clickLoginButton();
        // (Форма 2) Страница авторизации
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(user.getEmail());
        authUserPage.sendPassword(user.getPassword());
        authUserPage.clickLoginUserButton();
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.isCreateOrderButtonVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

    @Test
    @Description("Login user from registration page (successful)")
    public void loginUserFromRegistrationPageTest() {
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
        authUserPage.clickRegistrationUserButton();
        // (Форма 3) Страница регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.isAuthFormVisible();
        registrationPage.clickLoginButton();
        // (Форма 2) Страница авторизации
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(user.getEmail());
        authUserPage.sendPassword(user.getPassword());
        authUserPage.clickLoginUserButton();
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.isCreateOrderButtonVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

    @Test
    @Description("Login user from private office button (successful)")
    public void loginUserFromPrivateOfficeTest() {
        // Создаем данные пользователя для теста
        user = User.createRandom();
        ValidatableResponse response = userSteps.create(user);
        accessToken = response.extract().path("accessToken");
        // (Форма 1) Главная страница Stellarburgers
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        mainBurgerPage.open();
        mainBurgerPage.isLoginToPrivateOfficeButtonVisible();
        mainBurgerPage.clickLoginToPrivateOfficeButton();
        // (Форма 2) Страница авторизации
        AuthUserPage authUserPage = new AuthUserPage(driver);
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(user.getEmail());
        authUserPage.sendPassword(user.getPassword());
        authUserPage.clickLoginUserButton();
        // (Форма 1) Главная страница Stellarburgers
        mainBurgerPage.isCreateOrderButtonVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

    @Test
    @Description("Authorization user with incorrect password (filed)")
    public void loginUserIncorrectPassTest() {
        // (Форма 1) Главная страница Stellarburgers
        MainBurgerPage mainBurgerPage = new MainBurgerPage(driver);
        mainBurgerPage.open();
        mainBurgerPage.isLoginToAccountButtonVisible();
        mainBurgerPage.clickLoginToAccountButton();
        // (Форма 2) Страница авторизации
        AuthUserPage authUserPage = new AuthUserPage(driver);
        authUserPage.isAuthFormVisible();
        authUserPage.sendLoginName(authUserPage.LOGIN_IS_EXIST_VALUE);
        authUserPage.sendPassword(authUserPage.PASSWORD_INCORRECT_VALUE);
        authUserPage.clickLoginUserButton();
        authUserPage.isIncorrectPassFieldVisible();
        authUserPage.checkIncorrectPasswordText();
    }

    @Test
    @Description("Log out user (successful)")
    public void LogOutTest() {
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
        privateOfficePage.clickLogOutButton();
        // (Форма 2) Страница авторизации
        authUserPage.isAuthFormVisible();
        // Удаляем данные пользователя
        userSteps.removed(accessToken);
    }

}
