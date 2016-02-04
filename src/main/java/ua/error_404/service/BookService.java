package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> findPageBySpecifications(List<Specification<Book>> specifications, Pageable pageable) {
        SpecificationsBuilder<Book> builder = new SpecificationsBuilder<>(specifications);
        return bookRepository.findAll(builder.buildBySpecsConjunction(), pageable);
    }

}
