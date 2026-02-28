package br.com.cineverse.mapper;

import br.com.cineverse.controller.dto.request.UserRequestDTO;
import br.com.cineverse.controller.dto.response.UserResponseDTO;
import br.com.cineverse.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequestDTO userRequestDTO) {
        return User
                .builder()
                .name(userRequestDTO.name())
                .email(userRequestDTO.email())
                .password(userRequestDTO.password())
                .build();
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        return UserResponseDTO
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
