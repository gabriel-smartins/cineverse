package br.com.cineverse.controller;

import br.com.cineverse.controller.dto.request.UserRequestDTO;
import br.com.cineverse.controller.dto.response.UserResponseDTO;
import br.com.cineverse.entity.User;
import br.com.cineverse.mapper.UserMapper;
import br.com.cineverse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cineverse/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO request) {
        var user = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponseDTO(user));
    }
}
