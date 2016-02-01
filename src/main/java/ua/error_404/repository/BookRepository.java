package ua.error_404.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.error_404.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query(value = "SELECT * FROM `book` ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Book> findThreeByRandom();

    Book findById(Long id);


}
