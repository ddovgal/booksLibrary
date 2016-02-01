package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Author;
import ua.error_404.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> findThreeByRandom() {
        return authorRepository.findThreeByRandom();
    }

    public List<Author> findByNameContaining(String notFullName) {
        return authorRepository.findByNameContaining(notFullName);
    }

}
