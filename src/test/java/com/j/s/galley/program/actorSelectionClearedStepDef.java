package com.j.s.galley.program;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.j.s.galley.program.CommonStepDef.driver;

public class actorSelectionClearedStepDef {
    @When("the x is clicked on the search bar")
    public void the_x_is_clicked_on_the_search_bar() {
        driver.findElement(By.className("css-tj5bde-Svg")).click();
    }
    @Then("the actor information should be hidden")
    public void the_actor_information_should_be_hidden() {
        boolean actorInfoIsVisible = driver.findElement(By.id("actorInfoHidden")).isDisplayed();//empty div with this id should be rendered. Since its empty should return false
        Assertions.assertEquals(false, actorInfoIsVisible);
    }
    @Then("the search bar should no longer display the actor name")
    public void the_search_bar_should_no_longer_display_the_actor_name() {
        String actual = driver.findElement(By.id("react-select-2-placeholder")).getText();
        String expected = "Select...";//placeholder text
        Assertions.assertEquals(expected, actual);
        driver.quit();
    }
}
