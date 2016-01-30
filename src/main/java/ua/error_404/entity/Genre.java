package ua.error_404.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private short recommendedAge;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;


    protected Genre() {
    }

    public Genre(String name, short recommendedAge) {
        this.name = name;
        this.recommendedAge = recommendedAge;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(short recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
