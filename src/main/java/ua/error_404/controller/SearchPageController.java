package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.error_404.entity.Book;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;
import ua.error_404.specification.BookSpecifications;

import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping("/book")
    @ResponseBody
    public String findBooksByCriteria(
            String name,
            String author,
            String tags,
            String ratio,
            String sorting,
            Integer page,
            Integer state,
            Model model
    ) {
        String responseViewFile = "searchPage";
        if (state == -2) {
            model.addAttribute("state", -2);
            return responseViewFile;
        }

        List<Specification<Book>> specifications = new ArrayList<>();
        if (name != null) specifications.add(BookSpecifications.containsInName(name));
        if (author != null) specifications.add(BookSpecifications.containsInAuthorName(author));
        if (tags != null) {
            specifications.add(BookSpecifications.hasOneOfGenres(Arrays.asList(tags.split(","))));
        }
        if (ratio != null) {
            String[] ratioBorders = ratio.split(",");
            specifications.add(BookSpecifications.ratioInBorders(Short.parseShort(ratioBorders[0]), Short.parseShort(ratioBorders[1])));
        }
        Sort sort = null;
        if (sorting != null)
            sort = BookSpecifications.sortBy(sorting.substring(0, sorting.length() - 1), sorting.charAt(sorting.length() - 1) == 'A');
        model.addAttribute("requestSpecs", specifications);
        model.addAttribute("page", page + 1);
        model.addAttribute("books", bookService.findBySpecifications(specifications, sort));
        model.addAttribute("state", -1);
        //return responseViewFile;

        //region Temp
        List<Book> books = bookService.findBySpecifications(specifications, sort);
        return (books.isEmpty() ? "Nothing" : "Its: " + books.stream().map(Book::getName).collect(Collectors.joining(", ")) + ".");
        //endregion
    }

    /*@RequestMapping("/author")
    public String findAuthorsByCriteria(
            String name,
            Integer page,
            Integer state,
            Model model) {
        String responseViewFile = "searchPage";
        if (state == 2) {
            model.addAttribute("state", 2);
            return responseViewFile;
        }
        *//*if () specification = Specifications.where(specification).and();
        if () specification = Specifications.where(specification).and();*//*
        //model.addAttribute("authors", authorService.findByNameContaining(name));
        //model.addAttribute("status", ...);
        return responseViewFile;
    }*/

}
