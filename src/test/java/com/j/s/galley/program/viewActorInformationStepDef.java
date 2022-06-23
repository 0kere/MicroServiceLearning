package com.j.s.galley.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.j.s.galley.program.CommonStepDef.driver;

public class viewActorInformationStepDef {

    @Then("the actor information should be shown the to user")
    public void the_actor_information_should_be_shown_the_to_user() {
        //Check correct actor name
        String expectedName = "PENELOPE GUINESS";
        WebElement name = driver.findElement(By.id("actorName"));
        Assertions.assertEquals(expectedName, name.getText());

        //Check categories display
        List<WebElement> categoryList = driver.findElements(By.id("categoryList"));
        String[] expectedCategoryList = {
                "Categories", "Sports", "Games", "Documentary", "Family", "Children", "Horror",
                "Animation", "Comedy", "Foreign", "Sci-Fi", "Classics", "Music"
        };
        categoryList.forEach((e)->System.out.println(e.getText()));
        String[] actualCategoryList = categoryList.get(0).getText().lines().toArray(String[]::new);//converts string of entire list into array of each line
        Arrays.sort(actualCategoryList);
        Arrays.sort(expectedCategoryList);
        Assertions.assertArrayEquals(expectedCategoryList, actualCategoryList);

        //Check language display
        String[] expectedLanguageList = {
                "Languages", "English"
        };
        String[] actualLanguageList = driver.findElements(By.id("languageList")).get(0).getText().lines().toArray(String[]::new);
        Arrays.sort(expectedLanguageList);
        Arrays.sort(actualLanguageList);
        Assertions.assertArrayEquals(expectedLanguageList, actualLanguageList);

        driver.quit();
    }
}
