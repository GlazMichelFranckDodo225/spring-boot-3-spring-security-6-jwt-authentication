package com.dgmf.service;

import com.dgmf.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.HashMap;

public interface JwtService {
    String getToken(UserDetails savedUser);
    String AssignTokenSavedUser(HashMap<String, Object> extraClaims, UserDetails savedUser);
    Key getKey();
}
