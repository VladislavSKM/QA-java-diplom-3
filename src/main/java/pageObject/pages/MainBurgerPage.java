package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainBurgerPage {
    private static final String AUTH_USER_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private final By LOGIN_TO_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By LOGIN_TO_PRIVATE_OFFICE_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");
    private final By USER_ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");

    private WebDriver driver;

    public MainBurgerPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open page Stellarburgers")
    public MainBurgerPage open() {
        driver.get(AUTH_USER_PAGE_URL);
        return this;
    }

    @Step("Click login to account button")
    public MainBurgerPage clickLoginToAccountButton() {
        driver.findElement(LOGIN_TO_ACCOUNT_BUTTON).click();
        return this;
    }

    @Step("Click login to private office button")
    public MainBurgerPage clickLoginToPrivateOfficeButton() {
        driver.findElement(LOGIN_TO_PRIVATE_OFFICE_BUTTON).click();
        return this;
    }

    @Step("Check that login to account button is visible")
    public boolean isLoginToAccountButtonVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(LOGIN_TO_ACCOUNT_BUTTON));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that login to private office button is visible")
    public boolean isLoginToPrivateOfficeButtonVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(LOGIN_TO_ACCOUNT_BUTTON));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that create order button is visible")
    public boolean isCreateOrderButtonVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(USER_ORDER_BUTTON));
        return notFoundElement.isDisplayed();
    }

}
