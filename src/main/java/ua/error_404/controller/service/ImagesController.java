package ua.error_404.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.error_404.service.AuthorService;
import ua.error_404.service.BookService;

@Controller
@RequestMapping("/img")
public class ImagesController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @RequestMapping("/{type}/{id}")
    public ResponseEntity<byte[]> getAuthorImage(@PathVariable String type, @PathVariable Long id) {
        byte[] imageContent;
        switch (type) {
            case "book": {
                imageContent = bookService.findById(id).getPicture();
                break;
            }
            case "author": {
                imageContent = authorService.findById(id).getPicture();
                break;
            }
            default: imageContent = null;
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
