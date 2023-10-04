package org.example.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private By elementsBlock = By.xpath("//*[text()='Elements']");
    private By alertsFrameWindowsBlock = By.xpath("//*[text()='Alerts, Frame & Windows']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Нажатие на блок Elements")
    public void clickToElementsBlock() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(elementsBlock));
        driver.findElement(elementsBlock).click();
    }

    @Step("Нажатие на блок Alerts, Frame & Windows")
    public void clickToAlertsFrameWindowsBlock() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(alertsFrameWindowsBlock));
        driver.findElement(alertsFrameWindowsBlock).click();
    }
}
