package com.dgmf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/login")
    public String login() {
        return "Login from Public Endpoint";
    }

    @PostMapping("/register")
    public String register() {
        return "Register from Public Endpoint";
    }
}
