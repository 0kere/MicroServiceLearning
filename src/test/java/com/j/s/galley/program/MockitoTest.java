package com.j.s.galley.program;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    private MicroServiceLearningApplication microServiceApp;
    @Mock
    private IActorRepository actorRepository;
    @Mock
    private IFilmRepository filmRepository;
    @Mock
    ILanguageRepository languageRepository;
    @Mock ICategoryRepository categoryRepository;

    @BeforeEach
    void setUp()
    {
        microServiceApp = new MicroServiceLearningApplication(actorRepository, filmRepository, categoryRepository, languageRepository);
    }

    @Test
    public void getAllActors()
    {
        microServiceApp.getAllActors();
        verify(actorRepository).findAll();
    }
    @Test
    public void getActorAtID()
    {
        Actor actor = new Actor("Bart", "Simpson");
        Mockito.when(microServiceApp.getActorWithID(1)).thenReturn(Optional.of(actor));
        Optional<Actor> act = microServiceApp.getActorWithID(1);

        Assertions.assertEquals("Bart",act.get().getFirst_name());
        Assertions.assertEquals("Simpson",act.get().getLast_name());
    }
    @Test
    public void addActor()
    {
        Actor newActor = new Actor("test", "ing");
        String expected = "Saved";
        String actual = microServiceApp.saveActor(newActor.getFirst_name(), newActor.getLast_name());

        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);

        verify(actorRepository).save(actorArgumentCaptor.capture());

        actorArgumentCaptor.getValue();

        Assertions.assertEquals(expected, actual, "Actor not added to database");
    }
    @Test
    public void updateActor()
    {
        final Actor actor = new Actor("test", "ing");
        Optional<Actor> optionalActor = Optional.of(actor);
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        Actor update = new Actor("ing", "test");
        String actual = microServiceApp.update(1, update);
        String expected = "ing test";
        Assertions.assertEquals(actual, expected, "Actor was not updated");
    }
    @Test
    public void deleteActor()
    {
        final Actor actor = new Actor("test", "ing");
        Optional<Actor> optionalActor = Optional.of(actor);
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        microServiceApp.deleteActor(1);
        Mockito.verify(actorRepository).delete(actor);
    }
}
