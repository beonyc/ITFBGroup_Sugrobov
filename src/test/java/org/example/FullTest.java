package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.config.DataBase;
import org.example.steps.*;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;

@DisplayName("Полностью весь сценарий в одном тесте")
public class FullTest {
    private WebDriver driver;
    private MainPage mainPage;
    private ElementsPage elementsPage;
    private ElementsPage_TextBox elTextBox;
    private ElementsPage_Buttons elButtons;
    private AlertsFrameWindowsPage alertsPage;
    private AlertsFrameWindowsPage_BrowserWindows alBrowWin;
    private AlertsFrameWindowsPage_Alerts alAlert;
    private final DataBase dataBase = new DataBase();

    @Before
    public void setUp() {
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
        driver.get(dataBase.BASE_URL);
    }

    @Test
    @DisplayName("Проверка по всему сценарию")
    @Description("Здесь описан полный путь сценария, который был дан в задании")
    public void fullPathTest() {
        //2.	Нажать на «Elements»
        mainPage.clickToElementsBlock();
        //3.	Нажать на «Text box»
        elementsPage.clickToTextBox();
        //4.	Заполнить поля: Full Name, Email, Current Address, Permanent Address
        elTextBox.fillAllTextBoxItems(dataBase.NAME, dataBase.EMAIL, dataBase.CURRENT_ADDRESS, dataBase.PERMANENT_ADDRESS);
        //5.	Нажать на кнопку «Submit»
        elTextBox.clickSubmitButton();
        //6.	Проверить, что данные в блоке сохранены корректно
        MatcherAssert.assertThat("Проверка что в сохраненном тексте есть наши данные", elTextBox.getSavedData(), allOf(
                containsString(dataBase.NAME),
                containsString(dataBase.EMAIL),
                containsString(dataBase.CURRENT_ADDRESS),
                containsString(dataBase.PERMANENT_ADDRESS)
        ));
        //7.	Нажать на «Buttons»
        elementsPage.clickToButtons();
        //8.	Нажать на кнопку «Click me»
        elButtons.clickToDynamicButton();
        //9.	Проверить, что появился текст «You have done a dynamic click»
        Assert.assertEquals("You have done a dynamic click", elButtons.getDynamicClickMessage());
        //10.	Нажать на кнопку «Right Click me»
        elButtons.clickToRightClickButton();
        //11.	Проверить, что появился текст «You have done a right click»
        Assert.assertEquals("You have done a right click", elButtons.getRightClickMessage());
        //12.	Нажать на кнопку «Double Click me»
        elButtons.clickToDoubleClickButton();
        //13.	Проверить, что появился текст «You have done a double click»
        Assert.assertEquals("You have done a double click", elButtons.getDoubleClickMessage());
        //14.	Нажать на «Alerts, Frame & Windows»
        mainPage.clickToAlertsFrameWindowsBlock();
        //15.	Нажать на «Browser Windows»
        alertsPage.clickToBrowserWindows();
        //16.	Нажать на кнопку «New Tab»
        alBrowWin.clickToNewTabButton();
        //17.	Закрыть новую вкладку
        alBrowWin.closeNewTab();
        //18.	Нажать на кнопку «New window»
        alBrowWin.clickNewWindowButton();
        //19.	 Закрыть новое окно
        alBrowWin.closeNewWindow();
        //20.	Нажать на «Alerts»
        alertsPage.clickToAlerts();
        //21.	Нажать на кнопку «Click me»  рядом с Click Button to see alert
        alAlert.clickAlertButton();
        //22.	Закрыть уведомление
        alAlert.closeAlert();
        //23.	Нажать на кнопку «Click me»  рядом с On button click, alert will appear after 5 seconds
        alAlert.clickATimerAlertButton();
        //24.	Закрыть уведомление
        alAlert.closeAlertWithTimer();
        //25.	Нажать на кнопку «Click me»  рядом с On button click, confirm box will appear
        alAlert.clickConfirmButton();
        //26.	Нажать на кнопку «Да» в уведомление
        alAlert.pressOkInConfirmAlert();
        //27.	Проверить, что появился текст You selected Ok
        Assert.assertTrue(alAlert.getTextAfterConfirmAlertIsClosed().contains("You selected Ok"));
        //28.	Нажать на кнопку «Click me»  рядом с On button click, prompt box will appear
        alAlert.clickPromptButton();
        //29.	Заполнить поле в уведомление данными: Test name
        alAlert.setDataToPromptInput(dataBase.MESSAGE);
        //30.	Проверить, что появился текст You entered Test name
        Assert.assertTrue(alAlert.getTextAfterPromptAlertIsClosed().contains(String.format("You entered %s", dataBase.MESSAGE)));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}