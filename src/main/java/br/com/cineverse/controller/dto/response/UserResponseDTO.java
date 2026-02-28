package br.com.cineverse.controller.dto.response;

import lombok.Builder;

@Builder
public record UserResponseDTO(Long id, String name, String email) {
}
