import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.pages.AuthUserPage;
import pageObject.pages.MainBurgerPage;
import pageObject.pages.RegistrationPage;
import pageObject.user.User;

public class AuthUserTest {

    private WebDriver driver;
    private User user;


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Registration user (Success)")
    public void RegistrationUserSuccessTest() {
        // Создаем данные пользователя для теста
        user = User.createRandom();
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
        registrationPage.sendName(user.getName());
        registrationPage.sendEmail(user.getEmail());
        registrationPage.sendPassword(user.getPassword());
        registrationPage.clickRegistrationButton();
        // (Форма 1) Главная страница Stellarburgers
        authUserPage.isAuthFormVisible();
    }

    @Test
    @Description("Registration user with incorrect password (filed)")
    public void RegistrationUserIncorrectPassTest() {
        // Создаем данные пользователя для теста
        user = User.createRandom();
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
        registrationPage.sendName(registrationPage.NAME_IS_EXIST_VALUE);
        registrationPage.sendEmail(user.getEmail());
        registrationPage.sendPassword(authUserPage.PASSWORD_INCORRECT_VALUE);
        registrationPage.clickRegistrationButton();
        authUserPage.isIncorrectPassFieldVisible();
        authUserPage.checkIncorrectPasswordText();
    }

    @Test
    @Description("Registration user is exist password (filed)")
    public void RegistrationUserIsExistTest() {
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
        registrationPage.sendName(registrationPage.NAME_IS_EXIST_VALUE);
        registrationPage.sendEmail(registrationPage.EMAIL_IS_EXIST_VALUE);
        registrationPage.sendPassword(registrationPage.PASSWORD_IS_EXIST_VALUE);
        registrationPage.clickRegistrationButton();
        registrationPage.isUserIsExistHeaderVisible();
        registrationPage.checkIncorrectPasswordText();
    }
}
