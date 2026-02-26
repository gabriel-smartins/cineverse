package br.com.cineverse.controller.dto.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record StreamingRequestDTO(String name) {
}
