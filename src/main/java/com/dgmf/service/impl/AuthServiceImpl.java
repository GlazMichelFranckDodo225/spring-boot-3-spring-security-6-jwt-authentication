package com.dgmf.service.impl;

import com.dgmf.entity.Token;
import com.dgmf.entity.User;
import com.dgmf.entity.enums.Role;
import com.dgmf.entity.enums.TokenType;
import com.dgmf.repository.TokenRepository;
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
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // This Method allows to create a "User", save him into the DB and
    // returns, to the Client, the generated JWT Token related to him
    @Override
    public AuthResponse register(
            RegisterRequestUserDTO registerRequestUserDTO)
    {
        // Creates the "User" from the coming Request
        User user = User.builder()
                .firstName(registerRequestUserDTO.getFirstName())
                .lastName(registerRequestUserDTO.getLastName())
                .username(registerRequestUserDTO.getUsername())
                .email(registerRequestUserDTO.getEmail())
                .password(passwordEncoder.encode(
                                registerRequestUserDTO.getPassword()
                        )
                )
                .role(Role.ROLE_USER) // Default Role
                .build();

        // userRepository.save(user);
        // Saves the "User" that we've just created
        User savedUser = userRepository.save(user);

        // Generates the JWT Token for the just saved User
        var jwtTokenSavedUser = jwtService.getToken(user);

        // Saves or Persists the generated Token into the DB
        var SavesOrPersistsGeneratedToken = Token.builder()
                .user(savedUser)
                .token(jwtTokenSavedUser)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        // Saves the "Token" that we've just created
        tokenRepository.save(SavesOrPersistsGeneratedToken);

        System.out.println("Stack Trace - AuthServiceImpl - register() \nReturns the generated JWT Token to the Client");

        // Returns the generated JWT Token to the Client
        return AuthResponse.builder()
                .token(jwtTokenSavedUser)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequestUserDTO loginRequestUserDTO) {
        // The "AuthenticationManager" will check "Username" and "Password"
        // An Exception will throw if the "Username" or " Password"
        // are not correct
        authenticationManager.authenticate( /*
                .authenticate() ==> Method of the "Authentication Manager"
                which allows to authenticate "User" based on his
                "Username" and "Password"
                */
                new UsernamePasswordAuthenticationToken(
                        loginRequestUserDTO.getUsername(),
                        loginRequestUserDTO.getPassword()
                )
        );

        // Here, "Username" and "Password" are correct.
        // Next step ==> Find the User into the DB based on
        // his "Username" coming from the Request
        UserDetails userFound = userRepository
                .findByUsername(loginRequestUserDTO.getUsername()).orElseThrow();

        // Generates a JWT Token for the retrieved User
        String jwtTokenUserFound = jwtService.getToken(userFound);

        System.out.println("Stack Trace - AuthServiceImpl - login() \nReturns the JWT Token to the Client");

        // Returns the JWT Token to the Client
        return AuthResponse.builder()
                .token(jwtTokenUserFound)
                .build();
    }
}
