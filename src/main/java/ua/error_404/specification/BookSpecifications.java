package ua.error_404.specification;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import ua.error_404.entity.*;

import javax.persistence.criteria.SetJoin;
import java.util.List;

public final class BookSpecifications {

    private BookSpecifications() {
    }

    public static Specification<Book> containsInName(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get(Book_.name), "%" + name + "%");
    }

    public static Specification<Book> containsInAuthorName(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get(Book_.author).get(Author_.name), "%" + name + "%");
    }

    public static Specification<Book> hasOneOfGenres(List<String> genres) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true); //use to disable getting duplicates when more then 1 book's genre is in genres criteria search
            SetJoin<Book, Genre> bookGenres = root.join(Book_.genres);
            return bookGenres.get(Genre_.name).in(genres);
        };
    }

    public static Specification<Book> ratioInBorders(Short l, Short r) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(Book_.ratio), l, r);
    }

    public static Sort sortBy(String chosenField, boolean asc) {
        return new Sort((asc ? Sort.Direction.ASC : Sort.Direction.DESC), chosenField);
    }

}
