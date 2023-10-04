package org.example.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsPage {
    private WebDriver driver;

    private By textBoxSection = By.xpath("//li[@id='item-0']//span[text()='Text Box']");
    private By ButtonsSection = By.xpath("//li[@id='item-4']//span[text()='Buttons']");


    public ElementsPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Нажатие на блок TextBox")
    public void clickToTextBox() {
        driver.findElement(textBoxSection).click();
    }

    @Step("Нажатие на блок Buttons")
    public void clickToButtons() {
        driver.findElement(ButtonsSection).click();
    }

}


