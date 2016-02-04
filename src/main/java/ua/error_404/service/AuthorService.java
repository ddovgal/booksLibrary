package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Author;
import ua.error_404.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private static final int PAGE_SIZE = 5;

    @Autowired
    AuthorRepository authorRepository;

    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> findThreeByRandom() {
        return authorRepository.findThreeByRandom();
    }

    public Page<Author> findAllOrderByNameAsc(Integer page) {
        return authorRepository.findAll(new PageRequest(page, PAGE_SIZE, Sort.Direction.ASC, "name"));
    }

    public Page<Author> findPageByNameContaining(String name, Integer page) {
        Pageable pageable = new PageRequest(page, PAGE_SIZE, Sort.Direction.ASC, "name");
        return authorRepository.findByNameContaining(name, pageable);
    }

}
