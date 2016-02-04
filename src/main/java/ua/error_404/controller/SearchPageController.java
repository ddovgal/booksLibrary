package ua.error_404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import ua.error_404.entity.Author;
import ua.error_404.entity.Book;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;
import ua.error_404.service.GenreService;
import ua.error_404.specification.BookSpecifications;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    public String findBooksByCriteria(
            String name,
            String author,
            String tags,
            Short min,
            Short max,
            String same,
            String sorting,
            Integer page,
            Model model,
            HttpServletRequest request
    ) {
        String responseViewFile = "searchPage";
        if (page == -1) {
            model.addAttribute("state", -2);
            return responseViewFile;
        }

        model.addAttribute("state", -1);
        List<Specification<Book>> specifications = new ArrayList<>();
        if (name != null) specifications.add(BookSpecifications.containsInName(name));
        if (author != null) specifications.add(BookSpecifications.containsInAuthorName(author));
        if (tags != null && !tags.isEmpty()) {
            specifications.add(BookSpecifications.hasOneOfGenres(Arrays.asList(tags.split(","))));
        }
        if (!(min == null && max == null)) {
            if (min == null) min = 0;
            if (max == null) max = 10;
            if (same != null) min = max;
            specifications.add(BookSpecifications.ratioInBorders(min, max));
        }
        Pageable pageable = (sorting != null ?
                BookSpecifications.paginate(page - 1, BookSpecifications.sortBy(sorting.substring(0, sorting.length() - 1), sorting.charAt(sorting.length() - 1) == 'A')) :
                BookSpecifications.paginate(page - 1, BookSpecifications.sortBy("ratio", false)));
        Page<Book> booksPage = bookService.findPageBySpecifications(specifications, pageable);
        List<PageReferencer> list = generatePages(request, booksPage);
        model.addAttribute("pages", list);
        model.addAttribute("books", booksPage.getContent());
        //region Hack for Thymeleaf plugin - duplicate model properties
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("state", -1);
            context.setVariable("pages", list);
            context.setVariable("books", booksPage.getContent());
        }
        //endregion
        return responseViewFile;
        //region Temp
        /*List<Book> books = bookService.findPageBySpecifications(specifications, pageable).getContent();
        return (books.isEmpty() ? "Nothing" : "Its: " + books.stream().map(Book::getName).collect(Collectors.joining(", ")) + ".");*/
        //endregion
    }

    @RequestMapping("/author")
    public String findAuthorsByCriteria(
            String name,
            Integer page,
            Model model,
            HttpServletRequest request
    ) {
        String responseViewFile = "searchPage";
        if (page == -1) {
            model.addAttribute("state", 2);
            return responseViewFile;
        }

        model.addAttribute("state", 1);
        Page<Author> authorsPage = name == null ? authorService.findAllOrderByNameAsc(page - 1) : authorService.findPageByNameContaining(name, page - 1);
        List<PageReferencer> list = generatePages(request, authorsPage);
        model.addAttribute("pages", list);
        model.addAttribute("authors", authorsPage.getContent());
        //region Hack for Thymeleaf plugin - duplicate model properties
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("state", 1);
            context.setVariable("pages", list);
            context.setVariable("authors", authorsPage.getContent());
        }
        //endregion
        return responseViewFile;
    }

    private final class PageReferencer {
        private String pageNumber;
        private String reference;
        private String classType;

        public PageReferencer(String pageNumber, String reference, String classType) {
            this.pageNumber = pageNumber;
            this.reference = reference;
            this.classType = classType;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        public String getReference() {
            return reference;
        }

        public String getClassType() {
            return classType;
        }
    }

    private List<PageReferencer> generatePages(HttpServletRequest request, Page page) {

        String queryString = request.getQueryString();
        int l = queryString.indexOf("page=");
        int r = queryString.indexOf('&', l);
        queryString = queryString.replace(queryString.substring(l, r == -1 ? queryString.length() : r + 1), "");
        String prefix = request.getRequestURI() + "?" + queryString + "page=";

        int borderNumber = 3;
        LinkedList<PageReferencer> list = new LinkedList<>();
        int i = page.getNumber() + 1;
        list.add(new PageReferencer(Integer.toString(i), prefix + i, "active"));
        for (int j = 1; j <= borderNumber; j++) {
            if (i - j > 0) list.addFirst(new PageReferencer(Integer.toString(i - j), prefix + (i - j), null));
            if (i + j <= page.getTotalPages())
                list.addLast(new PageReferencer(Integer.toString(i + j), prefix + (i + j), null));
        }
        list.addFirst(new PageReferencer("« ", page.isFirst() ? "#" : prefix + (i - 1), "prev"));
        list.addLast(new PageReferencer("  »", page.isLast() ? "#" : prefix + (i + 1), "next"));
        return list;
    }

}
