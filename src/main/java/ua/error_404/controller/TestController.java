package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @Autowired
    GenreService genreService;

}
