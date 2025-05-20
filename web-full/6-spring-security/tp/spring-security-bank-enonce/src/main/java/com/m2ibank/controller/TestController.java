package com.m2ibank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/secured")
    public String securedEndpoint() {
        return "Accès autorisé au contenu sécurisé 🎉";
    }
}