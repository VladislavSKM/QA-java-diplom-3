import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    @Description("Login user from login to account button (successful)")
    public void loginUserFromLoginAccountButtonTest() {
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
    }

    @Test
    @Description("Login user from recovery password page (successful)")
    public void loginUserFromRecoveryPasswordPageTest() {
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
    }

    @Test
    @Description("Login user from registration page (successful)")
    public void loginUserFromRegistrationPageTest() {
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
    }

    @Test
    @Description("Login user from private office button (successful)")
    public void loginUserFromPrivateOfficeTest() {
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
    }

}
