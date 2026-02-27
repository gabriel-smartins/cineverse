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

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findMovieByCategories(List.of(Category
                .builder()
                .id(categoryId)
                .build()));
    }

    public Movie save(Movie movie) {

        var categories = findCategories(movie.getCategories());
        var streamings = findStreamings(movie.getStreamings());

        return movieRepository.save(movie.toBuilder()
                .categories(categories)
                .streamings(streamings)
                .build());
    }

    public Optional<Movie> update(Long id, Movie updateMovie) {
        var movieById = movieRepository.findById(id);

        if (movieById.isPresent()) {

            List<Category> categories = findCategories(updateMovie.getCategories());
            List<Streaming> streamings = findStreamings(updateMovie.getStreamings());

            Movie movie = movieById.get();
            var updatedMovie = movie.toBuilder()
                    .title(updateMovie.getTitle())
                    .description(updateMovie.getDescription())
                    .releaseDate(updateMovie.getReleaseDate())
                    .rating(updateMovie.getRating())
                    .categories(categories)
                    .streamings(streamings)
                    .build();

            movieRepository.save(updatedMovie);

            return Optional.of(movie);

        }
        return Optional.empty();
    }


    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    // Métodos auxiliares
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
