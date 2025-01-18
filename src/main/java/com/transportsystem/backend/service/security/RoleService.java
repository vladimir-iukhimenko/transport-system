package com.transportsystem.backend.service.security;

import com.transportsystem.backend.repository.security.RoleRepository;
import com.transportsystem.backend.model.security.ERole;
import com.transportsystem.backend.model.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleDAO(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

    @Transactional
    public Role getRoleByName(ERole name) {return roleRepository.getRoleByName(name);}

    @Transactional
    public Set<Role> convertToRolesSet(Set<String> stringRoles) {
        Set<Role> roles = new HashSet<>();

        if (stringRoles == null) {
            Role userRole = getRoleByName(ERole.ROLE_USER);
            // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            stringRoles.stream().forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = getRoleByName(ERole.ROLE_ADMIN);
                        System.out.println(">>>>>>>>>>>>>> GOT ADMIN!");
                        //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_MODERATOR":
                        Role modRole = getRoleByName(ERole.ROLE_MODERATOR);
                        System.out.println(">>>>>>>>>>>>>> GOT MODERATOR!");
                        //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = getRoleByName(ERole.ROLE_USER);
                        System.out.println(">>>>>>>>>>>>>> GOT USER!");
                        //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }

}
