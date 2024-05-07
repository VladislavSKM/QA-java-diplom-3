package pageObject.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthUserPage {
    private final By AUTH_FORM_HEADER = By.xpath(".//h2[text()='Вход']");
    private final By LOGIN_USER_BUTTON = By.xpath(".//button[text()='Войти']");
    private final By REGISTRATION_USER_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By RECOVERY_PASSWORD_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");
    private static final By LOGIN_NAME_FIELD = By.xpath(".//input[@name=\"name\"]");
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");
    private static final By INCORRECT_PASSWORD_FIELD = By.xpath(".//p[text()='Некорректный пароль']");
    public String INCORRECT_PASSWORD_ERROR_TEXT = "Некорректный пароль";
    public final String LOGIN_IS_EXIST_VALUE = "skmvlady@yandex.ru";
    public final String PASSWORD_INCORRECT_VALUE = "pass";
    private WebDriver driver;

    public AuthUserPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check that authorization header is visible")
    public boolean isAuthFormVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(AUTH_FORM_HEADER));
        return notFoundElement.isDisplayed();
    }
    @Step("Check that incorrect password field is visible")
    public boolean isIncorrectPassFieldVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD_FIELD));
        return notFoundElement.isDisplayed();
    }
    @Step("Check expected text and incorrect password")
    public void checkIncorrectPasswordText() {
        Assert.assertEquals("Значения не совпадают", INCORRECT_PASSWORD_ERROR_TEXT, getIncorrectPasswordText());
    }
    @Step("Get incorrect password text from selector")
    public String getIncorrectPasswordText() {
      return  driver.findElement(INCORRECT_PASSWORD_FIELD).getText();
    }
    @Step("Send login name")
    public AuthUserPage sendLoginName(String loginName) {
        WebElement orderNameFielld = driver.findElement(LOGIN_NAME_FIELD);
        orderNameFielld.clear();
        orderNameFielld.sendKeys(loginName);
        return this;
    }
    @Step("Send password")
    public AuthUserPage sendPassword(String papssword) {
        WebElement orderNameFielld = driver.findElement(PASSWORD_FIELD);
        orderNameFielld.clear();
        orderNameFielld.sendKeys(papssword);
        return this;
    }
    @Step("Click login user button")
    public AuthUserPage clickLoginUserButton() {
        driver.findElement(LOGIN_USER_BUTTON).click();
        return this;
    }
    @Step("Click registration login user button")
    public AuthUserPage clickRegistrationUserButton() {
        driver.findElement(REGISTRATION_USER_BUTTON).click();
        return this;
    }
    @Step("Click recovery password button")
    public AuthUserPage clickRecoveryPasswordButton() {
        driver.findElement(RECOVERY_PASSWORD_BUTTON).click();
        return this;
    }


}
