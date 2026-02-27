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

    @PostMapping
    public ResponseEntity<MovieResponseDTO> save(@RequestBody MovieRequestDTO request) {
        var savedMovie = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponseDTO(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll() {
        var movieList = movieService.getAll();
        return ResponseEntity.ok(movieList.stream()
                .map(MovieMapper::toMovieResponseDTO)
                .toList());
    }



}
