package com.dgmf.service.impl;

import com.dgmf.entity.User;
import com.dgmf.service.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String getToken(UserDetails savedUser) {
        return null;
    }
}
