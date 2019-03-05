package com.olliekrk.contactsapp;

import com.olliekrk.contactsapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserRestController {
    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("save")
    public User saveUser(@RequestParam String username,
                         @RequestParam String firstname,
                         @RequestParam String lastname,
                         @RequestParam String password,
                         @RequestParam int age) {
        User user = new User(username, firstname, lastname, password, age);
        service.saveUser(user);
        return user;
    }
}
