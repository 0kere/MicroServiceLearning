package com.j.s.galley.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@CrossOrigin(origins="*")
@RestController //Handles Get POST DELETE PUT requests
@RequestMapping("/home")
public class MicroServiceLearningApplication {
	@Autowired
	private IActorRepository actorRepository;
	@Autowired
	private IFilmRepository filmRepository;
	@Autowired
	private ICategoryRepository categoryRepository;
	@Autowired
	private ILanguageRepository languageRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(MicroServiceLearningApplication.class, args);
	}

	public MicroServiceLearningApplication(IActorRepository actorRepository, IFilmRepository filmRepository, ICategoryRepository categoryRepository,
										   ILanguageRepository languageRepository)
	{

		this.actorRepository = actorRepository;
		this.filmRepository = filmRepository;
		this.categoryRepository = categoryRepository;
		this.languageRepository = languageRepository;
	}
	//region Actor mappings
	@GetMapping("/all_actors")
	public @ResponseBody
	Iterable<Actor>getAllActors()
	{
		return actorRepository.findAll();
	}

	@GetMapping("/actor/{actor_id}")
	public @ResponseBody
	Optional<Actor> getActorWithID(@PathVariable("actor_id")int actorID)
	{
		return actorRepository.findById(actorID);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/actor/{actor_id}")
	public String deleteActor(@PathVariable("actor_id") int actorID) throws ResourceNotFoundException
	{
		Actor actor = actorRepository.findById(actorID).orElseThrow( () -> new ResourceNotFoundException("Actor not found for this ID :: " + actorID));
		actorRepository.delete(actor);
		return "Deleted";
	}

	@PostMapping("/actor")
	String saveActor(@RequestParam String first_name, @RequestParam String last_name)
	{
		Actor a = new Actor(first_name, last_name);
		actorRepository.save(a);
		return "Saved";
	}

	@PutMapping("/actor/{actor_id}")
	String update(@PathVariable("actor_id")int id, @RequestBody Actor actor) throws ResourceNotFoundException
	{
		Actor update = actorRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Employee not found for this ID :: "+ id) );
		update.setFirst_name(actor.first_name);
		update.setLast_name(actor.last_name);
		actorRepository.save(update);
		return actor.toString();
	}
	//endregion
	//region Film mappings
	@GetMapping("/all_films")
	public @ResponseBody
	Iterable<Film>getAllFilms()
	{
		return filmRepository.findAll();
	}

	@GetMapping("/film/{film_id}")
	public @ResponseBody
	Optional<Film> getFilmWithID(@PathVariable("film_id")int filmID)
	{
		return filmRepository.findById(filmID);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/film/{film_id}")
	public Map<String, Boolean> deleteFilm(@PathVariable("film_id") int filmID) throws ResourceNotFoundException
	{
		Film film = filmRepository.findById(filmID).orElseThrow( () -> new ResourceNotFoundException("Film not found for this ID :: " + filmID));
		filmRepository.delete(film);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PostMapping("/film")
	String saveFilm(@RequestBody Film film)
	{
		filmRepository.save(film);
		return "Saved";
	}

	@PutMapping("/film/{film_id}")
	String update(@PathVariable("film_id")int id, @RequestBody Film film) throws ResourceNotFoundException
	{
		Film update = filmRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Film not found for this ID :: "+ id) );
		update.setTitle(film.title);
		update.setDescription(film.description);
		update.setRelease_year(film.release_year);
		update.setLanguage_id(film.language_id);
//		update.setOriginal_language_id(film.original_language_id);
		update.setRental_duration(film.rental_duration);
		update.setLength(film.length);
		update.setReplacement_cost(film.replacement_cost);
		update.setRating(film.rating);
		update.setSpecial_features(film.special_features);
		filmRepository.save(update);
		return "Updated";
	}
	//endregion
	//region Category mappings
	@GetMapping("/all_categories")
	public @ResponseBody
	Iterable<Category>getAllCategories()
	{
		return categoryRepository.findAll();
	}

	@GetMapping("/category/{category_id}")
	public @ResponseBody
	Optional<Category> getCategoryWithID(@PathVariable("category_id")int categoryID)
	{
		return categoryRepository.findById(categoryID);
	}
	//endregion
	//region Language mappings
	@GetMapping("/all_languages")
	public @ResponseBody
	Iterable<Language>getAllLanguages()
	{
		return languageRepository.findAll();
	}

	@GetMapping("/language/{language_id}")
	public @ResponseBody
	Optional<Language> getLanguageWithID(@PathVariable("language_id")int languageID)
	{
		return languageRepository.findById(languageID);
	}
	//endregion
}
