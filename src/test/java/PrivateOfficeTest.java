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
    @Description("Check private office page (successful)")
    public void checkPrivateOfficePageTest() {
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
    }

    @Test
    @Description("Check transition to constructor page (successful)")
    public void checkTransitionToConstructorPageTest() {
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
    }

    @Test
    @Description("Check transition to Stellar-burger page (successful)")
    public void checkTransitionToStellarBurgerPageTest() {
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
    }
}