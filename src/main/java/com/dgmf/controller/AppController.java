package com.dgmf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {
    @PostMapping("/welcome")
    public String welcome() {
        return "Welcome from Secure Endpoint";
    }
}
