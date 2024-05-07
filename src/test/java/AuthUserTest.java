import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
        user = User.createRandom();
    }

    @After
    @Step("Закрываем браузер")
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Registration user (Success)")
    public void RegistrationUserSuccessTest() {
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
