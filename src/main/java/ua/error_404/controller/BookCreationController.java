package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.WebContext;
import ua.error_404.entity.Book;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;

import java.io.IOException;

@Controller
@RequestMapping("/new/book")
public class BookCreationController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @Autowired
    GenreService genreService;

    @RequestMapping
    public String createBook(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("book", new Book()); //for inflating by thymeleaf
        //region Hack for Thymeleaf plugin - duplicate model properties
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("authors", authorService.findAll());
            context.setVariable("genres", genreService.findAll());
            context.setVariable("book", new Book());
        }
        //endregion
        return "createBookPage";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveBook(Book book, MultipartFile image) {
        try {
            book.setPicture(image.getBytes());
        } catch (IOException e) {
            return "redirect:/";
        }
        bookService.save(book);
        return "redirect:/search/book?page=1";
    }

}
