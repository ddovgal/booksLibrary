package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;

@Controller
public class WelcomeController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("authors", authorService.findThreeByRandom());
        model.addAttribute("books", bookService.findThreeByRandom());
        model.addAttribute("genres", genreService.findFiveByRandom());
        //region Hack for Thymeleaf plugin - duplicate model properties
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("authors", authorService.findThreeByRandom());
            context.setVariable("books", bookService.findThreeByRandom());
            context.setVariable("genres", genreService.findFiveByRandom());
        }
        //endregion
        return "index";
    }

}
