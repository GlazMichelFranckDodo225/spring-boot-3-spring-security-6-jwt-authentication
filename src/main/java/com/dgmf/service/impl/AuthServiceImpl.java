package com.dgmf.service.impl;

import com.dgmf.entity.User;
import com.dgmf.entity.utilityclasses.Role;
import com.dgmf.repository.UserRepository;
import com.dgmf.service.AuthService;
import com.dgmf.dto.LoginRequestUserDTO;
import com.dgmf.dto.RegisterRequestUserDTO;
import com.dgmf.controller.auth.AuthResponse;
import com.dgmf.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse login(LoginRequestUserDTO loginRequestUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestUserDTO.getUsername(),
                        loginRequestUserDTO.getPassword()
                )
        );

        UserDetails userDetails = userRepository.findByUsername(
                loginRequestUserDTO.getUsername()
        ).orElseThrow();

        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse register(
            RegisterRequestUserDTO registerRequestUserDTO)
        {
        User user = User.builder()
                .firstName(registerRequestUserDTO.getFirstName())
                .lastName(registerRequestUserDTO.getLastName())
                .username(registerRequestUserDTO.getUsername())
                .email(registerRequestUserDTO.getEmail())
                .password(passwordEncoder.encode(
                        registerRequestUserDTO.getPassword()
                        )
                )
                .role(Role.USER) // Default Role
                .build();

        User savedUser = userRepository.save(user);
        // userRepository.save(user);

        // Return a Token after saving the User
        return AuthResponse.builder()
                .token(jwtService.getToken(savedUser))
                .build();
    }
}
