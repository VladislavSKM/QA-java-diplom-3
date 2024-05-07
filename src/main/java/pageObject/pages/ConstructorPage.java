package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorPage {
    private final By CREATE_BURGER_HEADER = By.xpath(".//h1[text()='Соберите бургер']");
    private final By BUNS_HEADER = By.xpath(".//h2[text()='Булки']");
    private final By SAUCE_HEADER = By.xpath(".//h2[text()='Соусы']");
    private final By FILLINGS_HEADER = By.xpath(".//h2[text()='Начинки']");
    private final By SAUCE_BUTTON = By.xpath(".//div[(@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]/span[text()='Соусы']");
    private final By FILLINGS_BUTTON = By.xpath(".//div[(@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]/span[text()='Начинки']");
    private final By BUNS_BUTTON = By.xpath(".//div[(@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]/span[text()='Булки']");
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

    @Step("Check that buns button is visible")
    public boolean isBunsButtonVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(BUNS_BUTTON));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that fillings button is visible")
    public boolean isFillingsButtonVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(FILLINGS_BUTTON));
        return notFoundElement.isDisplayed();
    }

    @Step("Check that sauce button is visible")
    public boolean isSauceButtonVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(SAUCE_BUTTON));
        return notFoundElement.isDisplayed();
    }

    @Step("Scroll to the sauce Header")
    public ConstructorPage scrollToTheSauceHeader() {
        WebElement questionHeader = driver.findElement(SAUCE_HEADER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionHeader);
        return this;
    }
    @Step("Scroll to the fillings Header")
    public ConstructorPage scrollToTheFillingsHeader() {
        WebElement questionHeader = driver.findElement(FILLINGS_HEADER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionHeader);
        return this;
    }
}
