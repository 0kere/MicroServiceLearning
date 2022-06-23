package com.j.s.galley.program;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IActorRepository extends CrudRepository<Actor, Integer> {
    List<Actor> findByFirstNameContaining(String searchParam);
}
