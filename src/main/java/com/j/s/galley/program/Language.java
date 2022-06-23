package com.j.s.galley.program;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int language_id;
    String name;

    public Language(int id, String name)
    {
        this.language_id = id;
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
