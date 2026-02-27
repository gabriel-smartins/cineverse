package br.com.cineverse.mapper;

import br.com.cineverse.controller.dto.request.MovieRequestDTO;
import br.com.cineverse.controller.dto.response.CategoryResponseDTO;
import br.com.cineverse.controller.dto.response.MovieResponseDTO;
import br.com.cineverse.controller.dto.response.StreamingResponseDTO;
import br.com.cineverse.entity.Category;
import br.com.cineverse.entity.Movie;
import br.com.cineverse.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequestDTO request) {

        List<Category> categories = request.categories().stream()
                .map(categoryId -> Category.builder()
                        .id(categoryId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder()
                        .id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponseDTO toMovieResponseDTO(Movie movie) {

        List<CategoryResponseDTO> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponseDTO)
                .toList();

        List<StreamingResponseDTO> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponseDTO)
                .toList();

        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
