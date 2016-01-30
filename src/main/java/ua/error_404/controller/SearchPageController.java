package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.error_404.entity.Author;
import ua.error_404.entity.Book;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
public class SearchPageController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @Autowired
    GenreService genreService;


    public String findByCriteria(
            @RequestParam(name = "", required = false)String s,
            @RequestParam(name = "", required = false)String s1,
            @RequestParam(name = "", required = false)String s2,
            @RequestParam(name = "", required = false)String s3,
            @RequestParam(name = "", required = false)String s4
            ) {
        return null;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam(name = "name", required = false)String name) {
        String result;
        result = "Searching \"" + (name == null ? "" : name + "\": " + authorService.findByNameContains(name).stream().map(Author::getName).collect(Collectors.joining(", ")) + ".");
        return result;
    }

    @RequestMapping("/smart")
    @ResponseBody
    public String smart(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "ratio", required = false) Integer ratio) {
        String result;
        List<Book> books = bookService.smartFind(name, ratio);
        result = (books.isEmpty() ? "Nothing" : "Its: " + books.stream().map(Book::getName).collect(Collectors.joining(", ")) + ".");
        return result;
    }

}
