package org.example.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsFrameWindowsPage_BrowserWindows {
    private WebDriver driver;

    public AlertsFrameWindowsPage_BrowserWindows(WebDriver driver) {
        this.driver = driver;
    }

    private By newTabButton = By.xpath("//button[text()='New Tab']");
    private By newWindowsButton = By.xpath("//button[text()='New Window']");

    @Step("Нажатие на New Tab")
    public void clickToNewTabButton() {
        driver.findElement(newTabButton).click();
    }
    @Step("Закрытие новой вкладки")
    public void closeNewTab() {
        String[] windows = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(windows[1]);
        driver.close();
    }

    @Step("Нажатие на New window")
    public void clickNewWindowButton() {
        String[] windows = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(windows[0]);
        driver.findElement(newWindowsButton).click();
    }

    @Step("Закрытие нового окна")
    public void closeNewWindow() {
        String[] windows = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(windows[1]);
        driver.close();
        driver.switchTo().window(windows[0]);

    }

}
