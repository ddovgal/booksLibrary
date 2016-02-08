package ua.error_404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Genre;
import ua.error_404.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        for (Genre genre :
                genreRepository.findAll()) {
            genres.add(genre);
        }
        return genres;
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Genre> findFiveByRandom() {
        return genreRepository.findFiveByRandom();
    }

}
