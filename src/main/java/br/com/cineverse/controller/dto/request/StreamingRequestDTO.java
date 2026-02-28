package br.com.cineverse.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder(toBuilder = true)
public record StreamingRequestDTO(
        @NotBlank(message = "Streaming name is required")
        @Size(max = 255, message = "Streaming name must not exceed 255 characters")
        String name) {
}
