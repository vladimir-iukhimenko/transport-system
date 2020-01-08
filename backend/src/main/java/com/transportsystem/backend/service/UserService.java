package com.transportsystem.backend.service;

import com.transportsystem.backend.dao.UserDAO;
import com.transportsystem.backend.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {this.userDAO = userDAO;}

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Transactional
    public void editUser(User user) {
        userDAO.editUser(user);
    }

    @Transactional
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);
        if(user != null) {return user;}
        throw new UsernameNotFoundException("User " + username + " not found!");
    }

}
