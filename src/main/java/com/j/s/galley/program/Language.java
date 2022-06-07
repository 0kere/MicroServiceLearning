package com.j.s.galley.program;

import javax.persistence.*;

@Entity
@Table(name="language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int language_id;
    String name;
    public Language(String name)
    {
        this.name = name;
    }
    public Language(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
