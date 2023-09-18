package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsFrameWindowsPage {
    private WebDriver driver;

    public AlertsFrameWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By browserWindows = By.xpath("//span[text()='Browser Windows']");
    private By alerts = By.xpath("//span[text()='Alerts']");

    @Step("Нажатие на Browser Windows")
    public void clickToBrowserWindows() {
        driver.findElement(browserWindows).click();
    }

    @Step("Нажатие на Alerts")
    public void clickToAlerts() {
        driver.findElement(alerts).click();
    }

}
