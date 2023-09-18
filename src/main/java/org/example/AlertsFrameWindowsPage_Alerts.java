package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsFrameWindowsPage_Alerts {
    private WebDriver driver;

    public AlertsFrameWindowsPage_Alerts(WebDriver driver) {
        this.driver = driver;
    }

    private By alertButton = By.xpath("//button[@id='alertButton']");
    private By timerAlertButton = By.xpath("//button[@id='timerAlertButton']");
    private By confirmButton = By.xpath("//button[@id='confirmButton']");
    private By confirmBoxText = By.cssSelector("#confirmResult");
    private By promptBoxButton = By.xpath("//button[@id='promtButton']");
    private By promptBoxText = By.cssSelector("#promptResult");


    @Step("Нажатие на кнопку Click me для обычного prompt alert")
    public void clickPromptButton() {
        driver.findElement(promptBoxButton).click();
    }

    @Step("Вводим данные в prompt и закрываем его")
    public void setDataToPromptInput(String message) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(message);
        alert.accept();

    }

    @Step("Получение текста, который появился после закрытия prompt alert ")
    public String getTextAfterPromptAlertIsClosed() {
        return driver.findElement(promptBoxText).getText();
    }

    @Step("Нажатие на кнопку Click me для обычного confirmButton")
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    @Step("Нажатие OK в confirm alert")
    public void pressOkInConfirmAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Step("Получение текста, который появился после закрытия confirm alert ")
    public String getTextAfterConfirmAlertIsClosed() {
        return driver.findElement(confirmBoxText).getText();
    }

    @Step("Нажатие на кнопку Click me для обычного alert")
    public void clickAlertButton() {
        driver.findElement(alertButton).click();
    }

    @Step("Закрыть уведомление Alert")
    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    @Step("Нажатие на кнопку Click me для обычного alert с таймером")
    public void clickATimerAlertButton() {
        driver.findElement(timerAlertButton).click();
    }

    @Step("Закрыть уведомление Alert с таймером")
    public void closeAlertWithTimer() {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
}
