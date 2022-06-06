package com.j.s.galley.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//13
@SpringBootApplication
@RestController //Handles Get POST DELETE PUT requests
@RequestMapping("/home")
public class MicroServiceLearningApplication {
	@Autowired
	private @Qualifier("actors") IActorRepository actorRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(MicroServiceLearningApplication.class, args);
	}

	public MicroServiceLearningApplication(@Qualifier("actors") IActorRepository actorRepository)
	{
		this.actorRepository = actorRepository;
	}

	@GetMapping("/All_Actors")
	public @ResponseBody
	Iterable<Actor>getAllActors()
	{
		return actorRepository.findAll();
	}
}
