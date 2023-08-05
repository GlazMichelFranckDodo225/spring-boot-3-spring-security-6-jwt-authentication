package com.dgmf.service;

import com.dgmf.dto.LoginRequestUserDTO;
import com.dgmf.dto.RegisterRequestUserDTO;
import com.dgmf.controller.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequestUserDTO loginRequest);
    AuthResponse register(RegisterRequestUserDTO registerRequest);
}
