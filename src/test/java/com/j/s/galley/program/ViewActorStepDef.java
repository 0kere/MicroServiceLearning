package com.j.s.galley.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class ViewActorStepDef extends CucumberSpringConfig{
    @Autowired
    MicroServiceLearningApplication service;
    @Given("I have the actors id")
    public void i_have_the_actors_id() {
        // Write code here that turns the phrase above into concrete actions
        microServiceApp.getActorWithID(1);
        throw new io.cucumber.java.PendingException();
    }
    @When("I make call to \\/actor\\/\\{id}")
    public void i_make_call_to_actor() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I receive the actor information")
    public void i_receive_the_actor_information() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
