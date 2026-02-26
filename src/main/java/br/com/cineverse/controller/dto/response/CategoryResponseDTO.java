package br.com.cineverse.controller.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record CategoryResponseDTO(Long id, String name) {
}
