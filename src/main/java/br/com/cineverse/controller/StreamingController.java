package br.com.cineverse.controller;

import br.com.cineverse.controller.dto.request.StreamingRequestDTO;
import br.com.cineverse.controller.dto.response.StreamingResponseDTO;
import br.com.cineverse.mapper.StreamingMapper;
import br.com.cineverse.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cineverse/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponseDTO>> getAll() {
        var streamings = streamingService.findAll();
        return ResponseEntity.ok(streamings.stream()
                .map(StreamingMapper::toStreamingResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseDTO> getById(@PathVariable("id") Long streamingId) {
        return streamingService.findById(streamingId)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponseDTO(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponseDTO> save(@RequestBody StreamingRequestDTO request) {
        var streaming = StreamingMapper.toStreaming(request);
        var savedStreaming = streamingService.save(streaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponseDTO(savedStreaming));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long streamingId) {
        streamingService.delete(streamingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
