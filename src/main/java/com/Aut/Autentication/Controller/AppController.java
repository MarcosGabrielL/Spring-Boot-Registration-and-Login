/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Aut.Autentication.Controller;

/**
 *
 * @author Marcos
 */
import com.Aut.Autentication.Repository.UserRepository;
import com.Aut.Autentication.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    
    @Autowired
        private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signupform";
    }

@PostMapping("/process_register")
public String processRegister(User user) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
     
    userRepo.save(user);
     
    return "registersuccess";
}

    @GetMapping("/users")
public String listUsers(Model model) {
    List<User> listUsers = userRepo.findAll();
    model.addAttribute("listUsers", listUsers);
     
    
    return "users";
}
}
