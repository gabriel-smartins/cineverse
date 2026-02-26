package br.com.cineverse.mapper;

import br.com.cineverse.controller.dto.request.StreamingRequestDTO;
import br.com.cineverse.controller.dto.response.StreamingResponseDTO;
import br.com.cineverse.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequestDTO streamingRequestDTO) {
        return Streaming
                .builder()
                .name(streamingRequestDTO.name())
                .build();
    }

    public static StreamingResponseDTO toStreamingResponseDTO(Streaming streaming) {
        return StreamingResponseDTO
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
