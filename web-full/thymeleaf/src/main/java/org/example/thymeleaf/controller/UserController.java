package org.example.thymeleaf.controller;


import org.example.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {


    @GetMapping("/user")
    public String user(Model model) {
        User user = new User("Flavian",76);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = Arrays.asList(
                new User("Maneo",56),
                new User("William",42),
                new User("Jack",78),
                new User("John",80),
                new User("Pierre", 34),
                new User("Mary",80)
        );

        model.addAttribute("users", users);
        return "liste";
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        List<User> users = Arrays.asList(
                new User("Maneo",17,"active"),
                new User("William",42,"active"),
                new User("Jack",78,"banned"),
                new User("John",80,"active"),
                new User("Pierre", 34,"inactive"),
                new User("Mary",80,"inactive")
        );
        model.addAttribute("liste", users);
        return "conditional";
    }


    @GetMapping("/iteration")
    public String iteration(Model model) {
        List<User> users = Arrays.asList(
                new User("Maneo",17,"active"),
                new User("William",42,"active"),
                new User("Jack",78,"banned"),
                new User("John",80,"active"),
                new User("Pierre", 34,"inactive"),
                new User("Mary",80,"inactive")
        );

        HashMap<String, Integer> map = new HashMap<>();

        users.stream().forEach(user ->
                map.put(user.getName(), user.getAge()));

        model.addAttribute("map", map);
        model.addAttribute("users", users);
        return "iteration";

    }

    @GetMapping("/append")
    public String append(Model model){
        List<User> users = Arrays.asList(
                new User("John Doe", 45, "active"),
                new User("Flavy Flament", 17,"active"),
                new User("Jane Do", 14,"banned"),
                new User("Alain Deloin", 78,"inactive")
        );

        model.addAttribute("message","Bonjour");
        model.addAttribute("success",false);
        model.addAttribute("users", users);
        return "conditional-append";
    }


    @GetMapping("/dynamique")
    public String dynamique(){
        return "dynamique";
    }




}
