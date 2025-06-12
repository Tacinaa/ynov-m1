package org.example.todolistback.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {



    @GetMapping("")
    public String getCurrentUser(Authentication auth) {
        System.out.println("🔍 test: " + auth);
       /* if (auth == null || !auth.isAuthenticated()) {
            return "Pas connecté";
        }*/
        return "Connecté en tant que : " + auth.getName();
    }

    @GetMapping("/test-todo")
    public String test(Authentication auth) {
        return "Rôles : " + auth.getAuthorities();
    }


}
