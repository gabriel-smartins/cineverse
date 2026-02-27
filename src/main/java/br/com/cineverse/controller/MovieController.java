package br.com.cineverse.controller;

import br.com.cineverse.controller.dto.request.MovieRequestDTO;
import br.com.cineverse.controller.dto.response.MovieResponseDTO;
import br.com.cineverse.mapper.MovieMapper;
import br.com.cineverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cineverse/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll() {
        var movieList = movieService.findAll();
        return ResponseEntity.ok(movieList.stream()
                .map(MovieMapper::toMovieResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getById(@PathVariable("id") Long movieId) {
        return movieService.findById(movieId)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponseDTO(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> save(@RequestBody MovieRequestDTO request) {
        var savedMovie = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponseDTO(savedMovie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable("id") Long movieId, @RequestBody MovieRequestDTO request) {
        return movieService.update(movieId, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponseDTO(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long movieId) {
        movieService.delete(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
