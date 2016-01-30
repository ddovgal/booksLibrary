package ua.error_404.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.error_404.entity.Author;
import ua.error_404.entity.Book;
import ua.error_404.entity.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Query(value = "SELECT * FROM `genre` ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Genre> findFiveByRandom();

    Genre findById(Long id);

    List<Genre> findByName(String name);

}
