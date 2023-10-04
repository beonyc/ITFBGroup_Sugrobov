package org.example.demoqaTests;


import io.cucumber.java.After;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.example.config.DataBase;
import org.example.steps.*;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.*;

public class FullCucumberTest {
    private WebDriver driver;
    private MainPage mainPage;
    private ElementsPage elementsPage;
    private ElementsPage_TextBox elTextBox;
    private ElementsPage_Buttons elButtons;
    private AlertsFrameWindowsPage alertsPage;
    private AlertsFrameWindowsPage_BrowserWindows alBrowWin;
    private AlertsFrameWindowsPage_Alerts alAlert;
    private final DataBase dataBase = new DataBase();

    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        elementsPage = new ElementsPage(driver);
        elTextBox = new ElementsPage_TextBox(driver);
        elButtons = new ElementsPage_Buttons(driver);
        alertsPage = new AlertsFrameWindowsPage(driver);
        alBrowWin = new AlertsFrameWindowsPage_BrowserWindows(driver);
        alAlert = new AlertsFrameWindowsPage_Alerts(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Given("Перейти на {string}")
    public void openDemoQa(String string) {
        driver.get(string);
    }

    @When("Нажать на «Elements»")
    public void clickToElements() {
        mainPage.clickToElementsBlock();
    }

    @And("Нажать на «Text box»")
    public void clickToTextBox() {
        elementsPage.clickToTextBox();
    }

    @And("Заполнить поля: Full Name, Email, Current Address, Permanent Address")
    public void fill_FullName_Email_CurrentAddress_PermanentAddress() {
        elTextBox.fillAllTextBoxItems(dataBase.NAME, dataBase.EMAIL, dataBase.CURRENT_ADDRESS, dataBase.PERMANENT_ADDRESS);
    }

    @And("Нажать на кнопку «Submit»")
    public void clickSubmit() {
        elTextBox.clickSubmitButton();
    }

    @Then("Проверить, что данные в блоке сохранены корректно")
    public void checkThatCreatedTextFormContainsEnteredData() {
        MatcherAssert.assertThat("Проверка что в сохраненном тексте есть наши данные", elTextBox.getSavedData(), allOf(
                containsString(dataBase.NAME),
                containsString(dataBase.EMAIL),
                containsString(dataBase.CURRENT_ADDRESS),
                containsString(dataBase.PERMANENT_ADDRESS)
        ));
    }

    @When("Нажать на «Buttons»")
    public void clickButtons() {
        elementsPage.clickToButtons();
    }

    @And("Нажать на кнопку «Click me»")
    public void clickClickMe() {
        elButtons.clickToDynamicButton();
    }

    @Then("Проверить, что появился текст1 {string}")
    public void checkIfTextAppearedAfterDynamicClick(String text) {
        Assert.assertEquals("Проверить, что появился текст \"You have done a dynamic click\"",
                text, elButtons.getDynamicClickMessage());
    }

    @When("Нажать на кнопку «Right Click me»")
    public void clickRightClickMe() {
        elButtons.clickToRightClickButton();
    }

    @Then("Проверить, что появился текст2 {string}")
    public void checkIfTextAppearedAfterRightClick(String text) {
        Assert.assertEquals("Проверить, что появился текст \"You have done a right click\"",
                text, elButtons.getRightClickMessage());
    }

    @When("Нажать на кнопку «Double Click me»")
    public void clickDoubleClickMe() {
        elButtons.clickToDoubleClickButton();
    }

    @Then("Проверить, что появился текст3 {string}")
    public void checkIfTextAppearedAfterDoubleClick(String text) {
        Assert.assertEquals("Проверить, что появился текст \"You have done a double click\"",
                text, elButtons.getDoubleClickMessage());
    }

    @When("Нажать на «Alerts, Frame & Windows»")
    public void clickAlertsFrameWindows() {
        mainPage.clickToAlertsFrameWindowsBlock();
    }

    @And("Нажать на «Browser Windows»")
    public void clickBrowserWindows() {
        alertsPage.clickToBrowserWindows();
    }

    @And("Нажать на кнопку «New Tab»")
    public void clickNewTab() {
        alBrowWin.clickToNewTabButton();
    }

    @Then("Закрыть новую вкладку")
    public void closeTab() {
        alBrowWin.closeNewTab();
    }

    @When("Нажать на кнопку «New window»")
    public void clickNewWindows() {
        alBrowWin.clickNewWindowButton();
    }

    @Then("Закрыть новое окно")
    public void closeWindow() {
        alBrowWin.closeNewWindow();
    }

    @When("Нажать на «Alerts»")
    public void clickAlerts() {
        alertsPage.clickToAlerts();
    }

    @And("Нажать на кнопку «Click me» рядом с Click Button to see alert")
    public void clickClickMeButtonNextToClickButtonToSeeAlert() {
        alAlert.clickAlertButton();
    }

    @Then("Закрыть уведомление")
    public void closeNotification() {
        alAlert.closeAlert();
    }

    @When("Нажать на кнопку «Click me» рядом с On button click, alert will appear after 5 seconds")
    public void clickClickMeButtonNextToAfter5SecondText() {
        alAlert.clickATimerAlertButton();
    }

    @Then("Закрыть уведомление с таймером")
    public void closeNotificationAfterTimer() {
        alAlert.closeAlertWithTimer();
    }

    @When("Нажать на кнопку «Click me» рядом с On button click, confirm box will appear")
    public void clickClickMeButtonNextToConfirmBoxWillAppearText() {
        alAlert.clickConfirmButton();
    }

    @And("Нажать на кнопку «Да» в уведомление")
    public void clickYesButton() {
        alAlert.pressOkInConfirmAlert();
    }

    @Then("Проверить, что появился текст4 {string}")
    public void checkIfTextAppearedAfterConfirmAlertAccepted(String text) {
        Assert.assertTrue("Проверить, что появился текст \"You selected Ok\"",
                alAlert.getTextAfterConfirmAlertIsClosed().contains(text));
    }

    @When("Нажать на кнопку «Click me» рядом с On button click, prompt box will appear")
    public void clickClickMeButtonNExtToPromptBoxWillAppearText() {
        alAlert.clickPromptButton();
    }

    @And("Заполнить поле в уведомление данными: {string}")
    public void fillInputWithGivenText(String text) {
        alAlert.setDataToPromptInput(text);
    }

    @Then("Проверить, что появился текст {string}")
    public void checkIfTextAppearedAfterFillingPromptAlert(String text) {
        Assert.assertEquals("Проверить, что появился текст \"You entered Test name\"",
                alAlert.getTextAfterPromptAlertIsClosed(), text);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed screenshot", new ByteArrayInputStream(screenshot));
        }
        driver.quit();
    }
}
