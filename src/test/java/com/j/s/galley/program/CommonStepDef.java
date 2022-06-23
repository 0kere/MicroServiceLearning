package com.j.s.galley.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonStepDef {
    public static WebDriver driver;
    public String URL="http://localhost:3000";
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @Given("The application is running")
    public void the_application_is_running() {
        driver = new ChromeDriver();
        driver.get(URL);
    }
    @When("an actor is selected from the dropdown")
    public void an_actor_is_selected_from_the_dropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("searchBar")));//Waits for page inital load
        dropdown.click();
        Actions enter = new Actions(driver);
        enter.sendKeys(Keys.chord(Keys.ENTER)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("actorInfo")));//Waits for the actor info to be displayed
    }
}
