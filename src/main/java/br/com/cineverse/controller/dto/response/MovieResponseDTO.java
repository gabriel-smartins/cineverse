package br.com.cineverse.controller.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
public record MovieResponseDTO(Long id,
                               String title,
                               String description,
                               LocalDate releaseDate,
                               BigDecimal rating,
                               List<CategoryResponseDTO> categories,
                               List<StreamingResponseDTO> streamings
) {
}
