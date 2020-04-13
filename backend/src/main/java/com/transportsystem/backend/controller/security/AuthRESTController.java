package com.transportsystem.backend.controller.security;

import com.transportsystem.backend.model.security.ERole;
import com.transportsystem.backend.model.security.Role;
import com.transportsystem.backend.model.security.User;
import com.transportsystem.backend.model.security.payload.request.LoginRequest;
import com.transportsystem.backend.model.security.payload.request.SignupRequest;
import com.transportsystem.backend.model.security.payload.response.JwtResponse;
import com.transportsystem.backend.model.security.payload.response.MessageResponse;
import com.transportsystem.backend.security.jwt.JwtUtils;
import com.transportsystem.backend.service.security.RoleService;
import com.transportsystem.backend.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/auth", produces = "application/json")
public class AuthRESTController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {this.authenticationManager = authenticationManager;}

    @Autowired
    public void setUserService(UserService userService) {this.userService = userService;}

    @Autowired
    public void setRoleService(RoleService roleService) {this.roleService = roleService;}

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {this.passwordEncoder = passwordEncoder;}

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {this.jwtUtils = jwtUtils;}

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                roles,
                user.getEmployee()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userService.isUserExists(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Логин уже занят!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.getRoleByName(ERole.ROLE_USER);
                   // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.getRoleByName(ERole.ROLE_ADMIN);
                                //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleService.getRoleByName(ERole.ROLE_MODERATOR);
                                //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleService.getRoleByName(ERole.ROLE_USER);
                                //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.addUser(user);

        return ResponseEntity.ok(new MessageResponse("Пользователь зарегистрирован!"));
    }

    @GetMapping("/users")
    public List<User> readAllUsers() {
        List<User> listUsers = userService.getAllUsers();
        return listUsers;
    }
}
