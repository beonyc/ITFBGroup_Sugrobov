package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.DataBase;
import org.example.steps.*;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;

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

    @Given("1.Перейти на {string}")
    public void openDemoQa(String string) {
        driver.get(string);
    }

    @When("2.Нажать на «Elements»")
    public void clickToElements() {
        mainPage.clickToElementsBlock();
    }

    @And("3.Нажать на «Text box»")
    public void clickToTextBox() {
        elementsPage.clickToTextBox();
    }

    @And("4.Заполнить поля: Full Name, Email, Current Address, Permanent Address")
    public void fill_FullName_Email_CurrentAddress_PermanentAddress() {
        elTextBox.fillAllTextBoxItems(dataBase.NAME, dataBase.EMAIL, dataBase.CURRENT_ADDRESS, dataBase.PERMANENT_ADDRESS);
    }

    @And("5.Нажать на кнопку «Submit»")
    public void clickSubmit() {
        elTextBox.clickSubmitButton();
    }

    @Then("6.Проверить, что данные в блоке сохранены корректно")
    public void checkThatCreatedTextFormContainsEnteredData() {
        MatcherAssert.assertThat("Проверка что в сохраненном тексте есть наши данные", elTextBox.getSavedData(), allOf(
                containsString(dataBase.NAME),
                containsString(dataBase.EMAIL),
                containsString(dataBase.CURRENT_ADDRESS),
                containsString(dataBase.PERMANENT_ADDRESS)
        ));
    }

    @When("7.Нажать на «Buttons»")
    public void clickButtons() {
        elementsPage.clickToButtons();
    }

    @And("8.Нажать на кнопку «Click me»")
    public void clickClickMe() {
        elButtons.clickToDynamicButton();
    }

    @Then("9.Проверить, что появился текст {string}")
    public void checkIfTextAppearedAfterDynamicClick(String text) {
        Assert.assertEquals(text, elButtons.getDynamicClickMessage());
    }

    @When("10.Нажать на кнопку «Right Click me»")
    public void clickRightClickMe() {
        elButtons.clickToRightClickButton();
    }

    @Then("11.Проверить, что появился текст {string}")
    public void checkIfTextAppearedAfterRightClick(String text) {
        Assert.assertEquals(text, elButtons.getRightClickMessage());
    }

    @When("12.Нажать на кнопку «Double Click me»")
    public void clickDoubleClickMe() {
        elButtons.clickToDoubleClickButton();
    }

    @Then("13.Проверить, что появился текст {string}")
    public void checkIfTextAppearedAfterDoubleClick(String text) {
        Assert.assertEquals(text, elButtons.getDoubleClickMessage());
    }

    @When("14.Нажать на «Alerts, Frame & Windows»")
    public void clickAlertsFrameWindows() {
        mainPage.clickToAlertsFrameWindowsBlock();
    }

    @And("15.Нажать на «Browser Windows»")
    public void clickBrowserWindows() {
        alertsPage.clickToBrowserWindows();
    }

    @And("16.Нажать на кнопку «New Tab»")
    public void clickNewTab() {
        alBrowWin.clickToNewTabButton();
    }

    @Then("17.Закрыть новую вкладку")
    public void closeTab() {
        alBrowWin.closeNewTab();
    }

    @When("18.Нажать на кнопку «New window»")
    public void clickNewWindows() {
        alBrowWin.clickNewWindowButton();
    }

    @Then("19.Закрыть новое окно")
    public void closeWindow() {
        alBrowWin.closeNewWindow();
    }

    @When("20.Нажать на «Alerts»")
    public void clickAlerts() {
        alertsPage.clickToAlerts();
    }

    @And("21.Нажать на кнопку «Click me» рядом с Click Button to see alert")
    public void clickClickMeButtonNextToClickButtonToSeeAlert() {
        alAlert.clickAlertButton();
    }

    @Then("22.Закрыть уведомление")
    public void closeNotification() {
        alAlert.closeAlert();
    }

    @When("23.Нажать на кнопку «Click me» рядом с On button click, alert will appear after 5 seconds")
    public void clickClickMeButtonNextToAfter5SecondText() {
        alAlert.clickATimerAlertButton();
    }

    @Then("24.Закрыть уведомление")
    public void closeNotificationAfterTimer() {
        alAlert.closeAlertWithTimer();
    }

    @When("25.Нажать на кнопку «Click me» рядом с On button click, confirm box will appear")
    public void clickClickMeButtonNextToConfirmBoxWillAppearText() {
        alAlert.clickConfirmButton();
    }

    @And("26.Нажать на кнопку «Да» в уведомление")
    public void clickYesButton() {
        alAlert.pressOkInConfirmAlert();
    }

    @Then("27.Проверить, что появился текст {string}")
    public void checkIfTextAppearedAfterConfirmAlertAccepted(String text) {
        Assert.assertTrue(alAlert.getTextAfterConfirmAlertIsClosed().contains(text));
    }

    @When("28.Нажать на кнопку «Click me» рядом с On button click, prompt box will appear")
    public void clickClickMeButtonNExtToPromptBoxWillAppearText() {
        alAlert.clickPromptButton();
    }

    @And("29.Заполнить поле в уведомление данными: {string}")
    public void fillInputWithGivenText(String text) {
        alAlert.setDataToPromptInput(text);
    }

    @Then("30.Проверить, что появился текст {string}")
    public void checkIfTextAppearedAfterFillingPromptAlert(String text) {
        Assert.assertEquals(alAlert.getTextAfterPromptAlertIsClosed(), text);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
