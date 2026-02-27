package br.com.cineverse.service;

import br.com.cineverse.entity.Category;
import br.com.cineverse.entity.Movie;
import br.com.cineverse.entity.Streaming;
import br.com.cineverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie) {

        var categories = findCategories(movie.getCategories());
        var streamings = findStreamings(movie.getStreamings());

        return movieRepository.save(movie.toBuilder()
                .categories(categories)
                .streamings(streamings)
                .build());
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();

        for (Category category : categories) {
            categoryService.findById(category.getId())
                    .ifPresent(categoriesFound::add);
        }

        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();

        for (Streaming streaming : streamings) {
            streamingService.findById(streaming.getId())
                    .ifPresent(streamingsFound::add);
        }

        return streamingsFound;
    }

}
