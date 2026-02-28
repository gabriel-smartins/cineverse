package br.com.cineverse.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MovieRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(max = 255, message = "Title must not exceed 255 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @NotNull(message = "Release date is required")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @NotNull(message = "Rating is required")
        @DecimalMin(value = "0.0", message = "Minimum rating is 0.0")
        @DecimalMax(value = "10.0", message = "Maximum rating is 10.0")
        BigDecimal rating,

        @NotEmpty(message = "Movie must have at least one category")
        List<@NotNull(message = "Category ID must not be null") Long> categories,

        List<@NotNull(message = "Streaming ID must not be null") Long> streamings
) {
}
