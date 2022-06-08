package com.j.s.galley.program;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class addActorStepDef {
    //region Create mock service
    private static MicroServiceLearningApplication microServiceApp;
    @Mock
    private static IActorRepository actorRepository;
    @Mock
    private static IFilmRepository filmRepository;
    @Mock
    static ILanguageRepository languageRepository;
    @Mock static ICategoryRepository categoryRepository;
    //endregion
    @Before
    public static void init()
    {
        actorRepository = mock(IActorRepository.class);
        microServiceApp = new MicroServiceLearningApplication(actorRepository, filmRepository, categoryRepository, languageRepository);
    }

    String first_name, last_name;
    @Given("I have the actors information")
    public void i_have_the_actors_information() {
        // Write code here that turns the phrase above into concrete actions
        first_name = "Peter";
        last_name = "Parker";
    }
    String actual;
    ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
    @When("I add the actor information to the table")
    public void i_add_the_actor_information_to_the_table() {
        // Write code here that turns the phrase above into concrete actions
        actual = microServiceApp.saveActor(first_name, last_name);
        verify(actorRepository).save(actorArgumentCaptor.capture());
    }
    @Then("I get the return string saved")
    public void i_get_the_return_string_saved() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals("Saved", actual, "Actor details were not saved correctly");
        Assertions.assertEquals("Peter", actorArgumentCaptor.getValue().getFirst_name());
        Assertions.assertEquals("Parker", actorArgumentCaptor.getValue().getLast_name());
    }
}
