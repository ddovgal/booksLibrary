package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Genre;
import ua.error_404.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public Genre findById(Long id) {
        return genreRepository.findById(id);
    }

    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Genre> findFiveByRandom() {
        return genreRepository.findFiveByRandom();
    }

}
