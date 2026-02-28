package br.com.cineverse.service;

import br.com.cineverse.entity.User;
import br.com.cineverse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        return userRepository.save(user
                .toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .build());
    }
}
