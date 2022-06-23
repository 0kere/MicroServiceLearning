package com.j.s.galley.program;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

//DB Actor Class
@Entity
@Table(name="actor")
public class Actor {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int actor_id; //no need to be in the constructor because it is a pk and is autogenerated
    @Column(name="first_name")
    String firstName;
    String last_name;

    @ManyToMany
    @JoinTable(name="film_actor", joinColumns = {
            @JoinColumn(name="actor_id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name="film_id", nullable = false)
    })
    Set<Film> films;

    public Actor(String firstName, String last_name)
    {
        this.firstName = firstName;
        this.last_name = last_name;
    }
    //special constructor
    public Actor(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getActor_id() {
        return actor_id;
    }

    public String toString()
    {
        return firstName + " " + last_name;
    }

    public Set<Film> getFilms() {
        return films;
    }
}
