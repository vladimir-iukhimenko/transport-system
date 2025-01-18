package com.transportsystem.backend.repository.security;

import com.transportsystem.backend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(@NotBlank @Pattern(regexp = "[a-zA-Z._]+", message = "Wrong username!") String username);

    Boolean existsByUsername(@NotBlank @Pattern(regexp = "[a-zA-Z._]+", message = "Wrong username!") String username);
}
