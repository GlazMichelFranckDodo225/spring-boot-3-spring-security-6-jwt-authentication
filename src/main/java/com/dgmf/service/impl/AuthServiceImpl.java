package com.dgmf.service.impl;

import com.dgmf.entity.User;
import com.dgmf.entity.utilityclasses.Role;
import com.dgmf.repository.UserRepository;
import com.dgmf.service.AuthService;
import com.dgmf.dto.LoginRequestUserDTO;
import com.dgmf.dto.RegisterRequestUserDTO;
import com.dgmf.entity.utilityclasses.AuthResponse;
import com.dgmf.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequestUserDTO loginRequestUserDTO) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequestUserDTO registerRequestUserDTO) {
        User user = User.builder()
                .firstName(registerRequestUserDTO.getFirstName())
                .lastName(registerRequestUserDTO.getLastName())
                .username(registerRequestUserDTO.getUsername())
                .email(registerRequestUserDTO.getEmail())
                .password(registerRequestUserDTO.getPassword())
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        // Return a Token after saving the User
        return AuthResponse.builder()
                .token(jwtService.getToken(savedUser))
                .build();
    }
}
