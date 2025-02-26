package com.transportsystem.backend.repository.security;

import com.transportsystem.backend.model.security.ERole;
import com.transportsystem.backend.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role getRoleByName(ERole name);

}
