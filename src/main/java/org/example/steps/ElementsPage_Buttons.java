package org.example.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class ElementsPage_Buttons {
    private WebDriver driver;


    public ElementsPage_Buttons(WebDriver driver) {
        this.driver = driver;
    }

    private By doubleClick = By.cssSelector("#doubleClickBtn");
    private By rightClick = By.cssSelector("#rightClickBtn");
    private By oneClick = By.xpath("//button[text()='Click Me']");
    private By doubleClickMessage = By.id("doubleClickMessage");
    private By rightClickMessage = By.id("rightClickMessage");
    private By oneClickMessage = By.id("dynamicClickMessage");

    @Step("нажатие на кнопку Double Click Me")
    public void clickToDoubleClickButton() {
        new Actions(driver).doubleClick(driver.findElement(doubleClick)).perform();
    }

    @Step("Получить сообщение после нажатия на Double Click Me")
    public String getDoubleClickMessage() {
        return driver.findElement(doubleClickMessage).getText();
    }

    @Step("Нажатие на кнопку Right click")
    public void clickToRightClickButton() {
        new Actions(driver).contextClick(driver.findElement(rightClick)).perform();
    }

    @Step("Получить сообщение после нажатия на Right click Me")
    public String getRightClickMessage() {
        return driver.findElement(rightClickMessage).getText();
    }

    @Step("Нажатие на кнопку Click Me")
    public void clickToDynamicButton() {
        new Actions(driver).click(driver.findElement(oneClick)).perform();
    }

    @Step("Получить сообщение после нажатия на Click Me")
    public String getDynamicClickMessage() {
        return driver.findElement(oneClickMessage).getText();
    }
}
