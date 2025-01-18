package com.transportsystem.backend.service.security;

import com.transportsystem.backend.repository.security.UserRepository;
import com.transportsystem.backend.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserDAO(UserRepository userRepository) {this.userRepository = userRepository;}

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {return user;}
        throw new UsernameNotFoundException("User " + username + " not found!");
    }

    @Transactional
    public Boolean isUserExists(String username) {return userRepository.existsByUsername(username);}

}
