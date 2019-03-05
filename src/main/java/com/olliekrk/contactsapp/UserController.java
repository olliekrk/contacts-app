package com.olliekrk.contactsapp;

import com.olliekrk.contactsapp.entities.User;
import com.olliekrk.contactsapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", "/home"})
    public String homepage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String authorizationForm(Model model) {
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/authenticateUser")
    public String authorizeUser(@ModelAttribute(name = "user") User user) {
        if (userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
            return "authorization";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registrationForm(User user) {
        return "registration";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        model.addAttribute("user", user);
        userRepository.save(user);

        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String modificationForm(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "modification";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "modification";
        }

        if(!userRepository.findById(user.getId()).get().getPassword().equals(user.getPassword())){
            return "modification";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
}
