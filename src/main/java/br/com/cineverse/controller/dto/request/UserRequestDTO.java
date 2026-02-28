package br.com.cineverse.controller.dto.request;

import lombok.Builder;

@Builder
public record UserRequestDTO(String name, String email, String password) {
}
