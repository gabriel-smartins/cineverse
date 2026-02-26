package br.com.cineverse.controller.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record StreamingResponseDTO(Long id, String name) {
}
