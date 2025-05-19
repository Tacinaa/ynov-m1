package org.example.springsecurityproduct.controller;


import org.example.springsecurityproduct.dto.BaseResponseDto;
import org.example.springsecurityproduct.model.User;
import org.example.springsecurityproduct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public BaseResponseDto registerUser(@RequestBody User user) {
        if(userService.createUser(user)) {
            return new BaseResponseDto("success");
        }else{
            return new BaseResponseDto("failed");
        }
    }



}
