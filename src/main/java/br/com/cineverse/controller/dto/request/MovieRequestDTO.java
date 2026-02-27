package br.com.cineverse.controller.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MovieRequestDTO(
        String title,
        String description,
        LocalDate releaseDate,
        BigDecimal rating,
        List<Long> categories,
        List<Long> streamings
) {
}
