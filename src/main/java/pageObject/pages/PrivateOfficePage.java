package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivateOfficePage {
    private final By PROFILE_HEADER = By.xpath(".//a[text()='Профиль']");
    private final By LOG_OUT_BUTTON = By.xpath(".//button[text()='Выход']");
    private final By STELLAR_BURGER_BUTTON = By.xpath(".//div[(@class = 'AppHeader_header__logo__2D0X2')]");
    private final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private WebDriver driver;

    public PrivateOfficePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check that profile header is visible")
    public boolean isProfileHeaderVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(PROFILE_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Click log out button")
    public PrivateOfficePage clickLogOutButton() {
        driver.findElement(LOG_OUT_BUTTON).click();
        return this;
    }

    @Step("Click constructor button")
    public PrivateOfficePage clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        return this;
    }

    @Step("Click Stellar burger button")
    public PrivateOfficePage clickStellarBurgerButton() {
        driver.findElement(STELLAR_BURGER_BUTTON).click();
        return this;
    }
}
