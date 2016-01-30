package ua.error_404.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.error_404.entity.Book;

public final class BookSpecification {

    public static Specification<Book> likeByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("name"), "%" + name + "%");
    }

    public static Specification<Book> isRatioAbove(Integer ratio) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.not(criteriaBuilder.lessThan(root.<Integer>get("ratio"), ratio));
    }

}
