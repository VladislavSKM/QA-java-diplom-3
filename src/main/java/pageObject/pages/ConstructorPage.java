package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorPage {
    private final By CREATE_BURGER_HEADER = By.xpath(".//h1[text()='Соберите бургер']");
    private final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private final By BUNS_HEADER = By.xpath(".//h2[text()='Булки']");
    private final By SAUCE_HEADER = By.xpath(".//h2[text()='Соусы']");
    private final By FILLINGS_HEADER = By.xpath(".//h2[text()='Начинки']");
    private final By SAUCE_BUTTON = By.xpath(".//div[(@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[text()='Соусы']");
    private final By FILLINGS_BUTTON = By.xpath(".//div[(@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[text()='Начинки']");
    private WebDriver driver;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check that create burger header is visible")
    public boolean isCreateBurgerHeaderVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(CREATE_BURGER_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that buns header is visible")
    public boolean isBunsHeaderVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(BUNS_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that sauce header is visible")
    public boolean isSauceHeaderVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(SAUCE_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that fillings header is visible")
    public boolean isFillingsHeaderVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(FILLINGS_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Click constructor button")
    public ConstructorPage clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        return this;
    }

    @Step("Click sauce button")
    public ConstructorPage clickSauceButton() {
        driver.findElement(SAUCE_BUTTON).click();
        return this;
    }

    @Step("Click fillings button")
    public ConstructorPage clickFillingsButton() {
        driver.findElement(FILLINGS_BUTTON).click();
        return this;
    }
}
