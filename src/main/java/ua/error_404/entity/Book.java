package ua.error_404.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private short ratio;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @Column(columnDefinition = "VARCHAR(500)")
    private String description;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] picture;


    protected Book() {
    }

    public Book(String name, Author author, Set<Genre> genres, String description) {
        this.name = name;
        this.author = author;
        this.genres = genres;
        this.description = description;
        ratio = 0;
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

    public short getRatio() {
        return ratio;
    }

    public void setRatio(short ratio) {
        this.ratio = ratio;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;
    }

}
