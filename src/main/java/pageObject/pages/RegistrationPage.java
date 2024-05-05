package pageObject.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.restassured.RestAssured.given;

public class RegistrationPage {
    private static final By LOGIN_NAME_FIELD = By.xpath(".//input[@name=\"name\"]");
    private static final By REGISTRATION_HEADER = By.xpath(".//h2[text()='Регистрация']");
    private static final By USER_IS_EXIST_HEADER = By.xpath(".//p[text()='Такой пользователь уже существует']");
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");
    private static final By EMAIL_FIELD = By.xpath(".//label[text()='Email']/following-sibling::input");
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By LOGIN_BUTTON = By.xpath(".//a[text()='Войти']");
    public final String NAME_IS_EXIST_VALUE = "Onshapes27";
    public final String EMAIL_IS_EXIST_VALUE = "skmvlad@yandex.ru";
    public final String PASSWORD_IS_EXIST_VALUE = "password";
    public String USER_IS_EXIST_ERROR_TEXT = "Такой пользователь уже существует";

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Send name")
    public RegistrationPage sendName(String name) {
        WebElement orderNameFielld = driver.findElement(LOGIN_NAME_FIELD);
        orderNameFielld.clear();
        orderNameFielld.sendKeys(name);
        return this;
    }

    @Step("Send password")
    public RegistrationPage sendPassword(String papssword) {
        WebElement orderNameFielld = driver.findElement(PASSWORD_FIELD);
        orderNameFielld.clear();
        orderNameFielld.sendKeys(papssword);
        return this;
    }

    @Step("Send email")
    public RegistrationPage sendEmail(String email) {
        WebElement orderNameFielld = driver.findElement(EMAIL_FIELD);
        orderNameFielld.clear();
        orderNameFielld.sendKeys(email);
        return this;
    }

    @Step("Click registration button")
    public RegistrationPage clickRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
        return this;
    }

    @Step("Click login button")
    public RegistrationPage clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Check that registration header is visible")
    public boolean isAuthFormVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(REGISTRATION_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that user is exist header is visible")
    public boolean isUserIsExistHeaderVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(USER_IS_EXIST_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Get user is exist text from selector")
    public String getIncorrectPasswordText() {
        return driver.findElement(USER_IS_EXIST_HEADER).getText();
    }

    @Step("Check expected text and user is exist header")
    public void checkIncorrectPasswordText() {
        Assert.assertEquals("Значения не совпадают", USER_IS_EXIST_ERROR_TEXT, getIncorrectPasswordText());
    }

}
