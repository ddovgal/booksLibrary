package ua.error_404.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.error_404.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    @Query(value = "SELECT * FROM `author` ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Author> findThreeByRandom();

    Author findById(Long id);

    Page<Author> findByNameContaining(String notFullName, Pageable pageable);

}
