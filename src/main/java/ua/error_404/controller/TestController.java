package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.error_404.entity.Author;
import ua.error_404.entity.Book;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @Autowired
    GenreService genreService;

    @RequestMapping("/b")
    public Book getBookById(Long id) {
        Book result = bookService.findById(id);
        return result;
    }

    @RequestMapping("/a")
    public Author getAuthorById(Long id) {
        Author result = authorService.findById(id);
        return result;
    }

}
