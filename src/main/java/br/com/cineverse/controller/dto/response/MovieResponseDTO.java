package br.com.cineverse.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
public record MovieResponseDTO(Long id,
                               String title,
                               String description,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                               LocalDate releaseDate,
                               BigDecimal rating,
                               List<CategoryResponseDTO> categories,
                               List<StreamingResponseDTO> streamings
) {
}
