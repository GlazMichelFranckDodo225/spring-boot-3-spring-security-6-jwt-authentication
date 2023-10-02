package com.dgmf.controller;

import org.springframework.web.bind.annotation.*;

// Can be access only by Admin
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping
    public String get() {
        return "GET::AdminController";
    }

    @PostMapping
    public String post() {
        return "POST::AdminController";
    }

    @PutMapping
    public String put() {
        return "PUT::AdminController";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE::AdminController";
    }
}
