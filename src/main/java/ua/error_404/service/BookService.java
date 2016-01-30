package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Book;
import ua.error_404.repository.BookRepository;
import ua.error_404.specification.BookSpecification;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByNameContains(String name) {
        return bookRepository.findByNameContains(name);
    }

    public List<Book> findThreeByRandom() {
        return bookRepository.findThreeByRandom();
    }

    public List<Book> smartFind(String name, Integer ratio) {
        Specifications<Book> specifications = null;
        if (name != null) specifications = Specifications.where(BookSpecification.likeByName(name));
        if (ratio != null) {
            if (specifications == null) specifications = Specifications.where(BookSpecification.isRatioAbove(ratio));
            else specifications = specifications.and(BookSpecification.isRatioAbove(ratio));
        }

        return bookRepository.findAll(specifications);
    }

}
