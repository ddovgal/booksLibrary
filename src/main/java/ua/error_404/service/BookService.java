package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Book;
import ua.error_404.repository.BookRepository;
import ua.error_404.specification.SpecificationsBuilder;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findThreeByRandom() {
        return bookRepository.findThreeByRandom();
    }

    public List<Book> findBySpecifications(List<Specification<Book>> specifications, Sort sort) {
        SpecificationsBuilder<Book> builder = new SpecificationsBuilder<>(specifications);
        if (sort != null) return bookRepository.findAll(builder.buildBySpecsConjunction(), sort);
        else return bookRepository.findAll(builder.buildBySpecsConjunction());
    }

}
