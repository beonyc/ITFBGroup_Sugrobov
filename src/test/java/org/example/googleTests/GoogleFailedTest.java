package org.example.googleTests;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;


public class GoogleFailedTest {
    WebDriver driver;

    @Given("Перейти на {string}")
    public void goToGoogle(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("Я ввожу {string} в поисковую строку")
    public void fillSearchFieldWithText(String searchText) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchText);
    }

    @And("Я нажимаю клавишу Enter")
    public void pressEnter() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.submit();
    }

    @Then("Я вижу результаты поиска")
    public void seeTheResults() {
        //такого локатора нет, тут будет фейл
        driver.findElement(By.xpath("random-locator-path"));

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
