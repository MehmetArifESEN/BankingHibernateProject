package com.banking.controller;

import com.banking.app.BAUtils;
import com.banking.entity.User;
import com.banking.service.UserService;

import java.util.Optional;

public class UserController {

    private UserService userService;

    public UserController(){
        this.userService = new UserService();
    }

    public void saveUser(){
        String name = BAUtils.readString("İsminizi Girin");
        String email = BAUtils.readString("Email Girin");
        String password = BAUtils.readString("Passwordunuzu Girin");

        User user = new User(name,email,password);
        userService.save(user);
    }

    public User login() {
        //email ile login olalaım
        String email = BAUtils.readString("Email: ");
        String password = BAUtils.readString("Password: ");
        Optional<User> userOptional =  userService.findByEmail(email);
        return userOptional.get();
    }
}