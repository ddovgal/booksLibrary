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

    /*@RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam(name = "name", required = false)String name) {
        String result;
        result = "Searching \"" + (name == null ? "" : name + "\": " + authorService.findByNameContains(name).stream().map(Author::getName).collect(Collectors.joining(", ")) + ".");
        return result;
    }*/

    /*@RequestMapping("/smart")
    @ResponseBody
    public String smart(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "ratio", required = false) Short ratio) {
        String result;
        List<Book> books = bookService.smartFind(name, ratio);
        result = (books.isEmpty() ? "Nothing" : "Its: " + books.stream().map(Book::getName).collect(Collectors.joining(", ")) + ".");
        return result;
    }*/

    /*@RequestMapping("/genres")
    @ResponseBody
    public String smart(String genres) {
        List<Book> books = bookService.hasOneOfGenres(Arrays.asList(genres.split(",")));
        return (books.isEmpty() ? "Nothing" : "Its: " + books.stream().map(Book::getName).collect(Collectors.joining(", ")) + ".");
    }*/

    /*@RequestMapping("/smart")
    @ResponseBody
    public String smart(*//*@RequestParam(name = "name", required = false) *//*String name, @RequestParam(required = false) Integer r) {
        List<String> results = Arrays.asList(name.split(","));
        List<Book> books = bookService.smartFind(results.get(0), Short.parseShort(results.get(1)));
        return (books.isEmpty() ? "Nothing" : "Its: " + books.stream().map(Book::getName).collect(Collectors.joining(", ")) + ".");
    }*/

}
