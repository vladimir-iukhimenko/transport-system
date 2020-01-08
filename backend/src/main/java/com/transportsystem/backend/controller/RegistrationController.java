package com.transportsystem.backend.controller;

import com.transportsystem.backend.security.User;
import com.transportsystem.backend.service.EmployeeService;
import com.transportsystem.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private EmployeeService employeeService;

    @Autowired
    public void setUserService(UserService userService) {this.userService = userService;}

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {this.passwordEncoder = passwordEncoder;}

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {this.employeeService = employeeService;}

    @GetMapping
    public String registrationform(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "registration";
    }

    @PostMapping
    public String registrationprocess(@ModelAttribute("user") User user, @RequestParam("password") String password) {
        user.setPassword(passwordEncoder.encode(password));
        userService.addUser(user);
        return "redirect:/login";
    }
}
