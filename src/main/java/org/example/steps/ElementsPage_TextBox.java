package org.example.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ElementsPage_TextBox {
    private WebDriver driver;
    private By fullNameInput = By.xpath("//input[@placeholder='Full Name']");
    private By emailInput = By.xpath("//input[@placeholder='name@example.com']");
    private By currentAddressInput = By.xpath("//textarea[@placeholder='Current Address']");
    private By permanentAddressInput = By.id("permanentAddress");
    private By submitButton = By.id("submit");
    private By answerListItem = By.className("mb-1");
    StringBuilder savedData = new StringBuilder();

    public ElementsPage_TextBox(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнить поле Full Name")
    public void fillNameInput(String name) {
        driver.findElement(fullNameInput).sendKeys(name);
    }

    @Step("Заполнить поле email")
    public void fillEmailInput(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнить поле Current Address")
    public void fillCurrentAddressInput(String CurrentAddress) {
        driver.findElement(currentAddressInput).sendKeys(CurrentAddress);
    }

    @Step("Заполнить поле Permanent Address")
    public void fillPermanentAddressInput(String PermanentAddress) {
        driver.findElement(permanentAddressInput).sendKeys(PermanentAddress);
    }

    @Step("Заполнить все поля для TextBox")
    public void fillAllTextBoxItems(String name, String email, String CurrentAddress, String PermanentAddress) {
        fillNameInput(name);
        fillEmailInput(email);
        fillCurrentAddressInput(CurrentAddress);
        fillPermanentAddressInput(PermanentAddress);
    }

    @Step("Нажать на кнопку Submit")
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    @Step("Получить список сохранённых данных")
    public String getSavedData() {
        for (WebElement element : driver.findElements(answerListItem)) {
            savedData.append(element.getText());
        }
        return savedData.toString();
    }
}
