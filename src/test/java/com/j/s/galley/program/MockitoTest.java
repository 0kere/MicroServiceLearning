package com.j.s.galley.program;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    @InjectMocks
    private MicroServiceLearningApplication microServiceApp;
    @Mock
    private IActorRepository actorRepository;
    @Mock
    private IFilmRepository filmRepository;
    @Mock
    ILanguageRepository languageRepository;
    @Mock ICategoryRepository categoryRepository;

    @Rule //Ensures there are no unused stubs or potential stubbing issues e.g. method called without stubbing or incorrect args
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    //region Actor Tests
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

        Assertions.assertEquals("Bart",act.get().getFirstName());
        Assertions.assertEquals("Simpson",act.get().getLast_name());
    }
    @Test
    public void addActor()
    {
        Actor newActor = new Actor("test", "ing");
        String expected = "Saved";
        String actual = microServiceApp.saveActor(newActor.getFirstName(), newActor.getLast_name());

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
        Mockito.when(actorRepository.findById(anyInt())).thenReturn(optionalActor);
        String actual = microServiceApp.deleteActor(1);
        Mockito.verify(actorRepository).delete(actor);
        Assertions.assertEquals("Deleted", actual, "Actor not deleted successfully");
    }
    //endregion
    //region Film Tests
    @Test
    public void getAllFilms()
    {
        microServiceApp.getAllFilms();
        verify(filmRepository).findAll();
    }
    @Test void getFilmByID()
    {
        Film film = GetTestFilm();
        Mockito.when(microServiceApp.getFilmWithID(1)).thenReturn(Optional.of(film));
        Optional<Film> testFilm = microServiceApp.getFilmWithID(1);

        Date release = new Date(2002,1,1);
        Assertions.assertEquals("Spider-Man",testFilm.get().getTitle());
        Assertions.assertEquals("Peter Parker's life changes when he is bitten by a genetically altered spider and gains superpowers. He uses his powers to help people and finds himself facing the Green Goblin, an evil maniac.",
                testFilm.get().getDescription());
        Assertions.assertEquals(release, testFilm.get().getRelease_year());
        Assertions.assertEquals("English", testFilm.get().getLanguage().getName());
        Assertions.assertEquals(null, testFilm.get().getOriginal_language_id());
        Assertions.assertEquals(0.99f, testFilm.get().getRental_rate());
        Assertions.assertEquals(121, testFilm.get().getLength());
        Assertions.assertEquals(20.99f, testFilm.get().getReplacement_cost());
        Assertions.assertEquals("PG-13", testFilm.get().getRating());
        Assertions.assertEquals("Deleted Scenes", testFilm.get().getSpecial_features());
    }
    @Test
    public void deleteFilm()
    {
        final Film film = GetTestFilm();
        Optional<Film> optionalFilm = Optional.of(film);
        Mockito.when(filmRepository.findById(1)).thenReturn(optionalFilm);
        microServiceApp.deleteFilm(1);
        Mockito.verify(filmRepository).delete(film);
    }
    @Test
    public void addFilm()
    {
        Film film = GetTestFilm();
        String expected = "Saved";
        String actual = microServiceApp.saveFilm(film);

        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);

        verify(filmRepository).save(filmArgumentCaptor.capture());

        filmArgumentCaptor.getValue();

        Assertions.assertEquals(expected, actual, "Actor not added to database");
    }
    @Test
    public void updateFilm()
    {
        final Film film = GetTestFilm();
        Optional<Film> optionalFilm = Optional.of(film);
        Mockito.when(filmRepository.findById(1)).thenReturn(optionalFilm);
        Film update = GetTestFilm();
        String actual = microServiceApp.update(1, update);
        String expected = "Updated";
        Assertions.assertEquals(expected, actual, "Film was not updated");
    }
    Film GetTestFilm()
    {
        Date release = new Date(2002,1,1);
        Language newLanguage = new Language(0, "English");
        Film film = new Film("Spider-Man", "Peter Parker's life changes when he is bitten by a genetically altered spider and gains superpowers. He uses his powers to help people and finds himself facing the Green Goblin, an evil maniac.",
                release, newLanguage, null, 6, 0.99f, 121, 20.99f, "PG-13", "Deleted Scenes");
        Actor a = new Actor();
        a.films.add(film);
        film.actors.add(a);
        return film;
    }
    //endregion
    //region Category Tests
    @Test
    public void getAllCategories()
    {
        microServiceApp.getAllCategories();
        verify(categoryRepository).findAll();
    }
    @Test void getCategoryByID()
    {
        Category category = new Category("Muppets");
        Mockito.when(microServiceApp.getCategoryWithID(1)).thenReturn(Optional.of(category));
        Optional<Category> testCategory = microServiceApp.getCategoryWithID(1);

        Assertions.assertEquals("Muppets", testCategory.get().getName());
    }
    @Test
    public void newCategory()
    {
        Category category = new Category("Muppets");
        String expected = "Added";
        String actual = microServiceApp.newCategory("Muppets");

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);

        verify(categoryRepository).save(categoryArgumentCaptor.capture());

        categoryArgumentCaptor.getValue();

        Assertions.assertEquals(expected, actual, "Category not added to database");
    }
    //endregion
    //region Language Tests
    @Test
    public void getAllLanguages()
    {
        microServiceApp.getAllLanguages();
        verify(languageRepository).findAll();
    }
    @Test void getLanguageByID()
    {
        Language language = new Language(0, "Yoruba");
        Mockito.when(microServiceApp.getLanguageWithID(1)).thenReturn(Optional.of(language));
        Optional<Language> testLanguage = microServiceApp.getLanguageWithID(1);

        Assertions.assertEquals("Yoruba", testLanguage.get().getName());
    }
    @Test
    public void newLanguage()
    {
        Language language = new Language(1,"Yoruba");
        String expected = "Added";
        String actual = microServiceApp.newLanguage(language);

        ArgumentCaptor<Language> languageArgumentCaptor = ArgumentCaptor.forClass(Language.class);

        verify(languageRepository).save(languageArgumentCaptor.capture());

        languageArgumentCaptor.getValue();

        Assertions.assertEquals(expected, actual, "Language not added to database");
    }
    //endregion
}
