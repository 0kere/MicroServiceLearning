package com.j.s.galley.program;

import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="film")
public class Film implements Serializable {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int film_id;
    String title;
    @Nullable
    String description;
    @Nullable
    java.sql.Date release_year;
//    int language_id;
    @Nullable
    Integer original_language_id;
    int rental_duration;
    float rental_rate;
    @Nullable
    int length;
    float replacement_cost;
    @Nullable
    String rating;
    @Nullable
    String special_features;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany
    @JoinTable(name="film_actor", joinColumns = {
            @JoinColumn(name="film_id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name="actor_id", nullable = false)
    })
    Set<Actor> actors = new HashSet<>();
//region Failed attempts at relationships.
// Kept getting a SQLSyntaxErrorException. e.g. for language it was trying to do language.film for some reason. couldnt figure out why this happened

//    @JsonIgnore
//    public Set<Actor> getActors() {return actors;}

//    @ManyToMany
//    @JoinTable(
//            name = "film_category",
//            joinColumns = {
//                    @JoinColumn(name = "film_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name="category_id")
//            }
//    )
//    Set<Category> categories = new HashSet<Category>();
//endregion
    public Film(String title, String description, java.sql.Date release_year, Language language, Integer original_language_id,
                int rental_duration, float rental_rate, int length, float replacement_cost, String rating, String special_features)
    {
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.language = language;
        this.original_language_id = original_language_id;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.special_features = special_features;
    }
    public Film(){}

//    public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getRelease_year() {
        return release_year;
    }

    public void setRelease_year(java.sql.Date release_year) {
        this.release_year = release_year;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getOriginal_language_id() {
        return original_language_id;
    }

    public void setOriginal_language_id(Integer original_language_id) {
        this.original_language_id = original_language_id;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public float getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(float rental_rate) {
        this.rental_rate = rental_rate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(float replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
