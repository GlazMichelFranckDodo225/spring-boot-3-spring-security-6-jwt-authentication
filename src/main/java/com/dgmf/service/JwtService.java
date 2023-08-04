package com.dgmf.service;

import com.dgmf.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails savedUser);
}
