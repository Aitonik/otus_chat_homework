package com.otuschat.controller;


import com.otuschat.config.UserPrincipal;
import com.otuschat.dao.UserRepository;
import com.otuschat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home1() {

        return "index";
    }

//    @GetMapping("/403")
//    public String error403() {
//        return "/error/403";
//    }

    @GetMapping("/all")
    public String showUserList(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }


    @GetMapping("/login")
    public String showLoginForm(User user) {

        return "login";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user, Model model) {

        User u = new User(null, "", "", "", "0", "", "", "");
        model.addAttribute("user", u);
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user, Model model) {
        String pass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);

        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/info")
    public String info(User user, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findById(((UserPrincipal)auth.getPrincipal()).getId());
        model.addAttribute("user", u);
        return "user-info";
    }

    @GetMapping("/user/{id}")
    public String info(User user, Model model, @PathVariable(name = "id") String id) {

        User u = userRepository.findById(Long.parseLong(id));
        model.addAttribute("user", u);
        return "user-info";
    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        model.addAttribute("user", user);
//
//        return "update-user";
//    }

//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//
//        userRepository.save(user);
//
//        return "redirect:/index";
//    }

//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        userRepository.delete(user);
//
//        return "redirect:/index";
//    }
}