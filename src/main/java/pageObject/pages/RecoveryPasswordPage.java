package pageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoveryPasswordPage {
    private final By RECOVERY_PASSWORD_HEADER = By.xpath(".//h2[text()='Восстановление пароля']");
    private final By LOGIN_BUTTON = By.xpath(".//a[text()='Войти']");
    private WebDriver driver;

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check that recovery password header is visible")
    public boolean isAuthFormVisible() {
        WebElement notFoundElement = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(RECOVERY_PASSWORD_HEADER));
        return notFoundElement.isDisplayed();
    }

    @Step("Click login button")
    public RecoveryPasswordPage clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }
}
