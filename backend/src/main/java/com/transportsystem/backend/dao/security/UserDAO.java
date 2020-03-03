package com.transportsystem.backend.dao.security;

import com.transportsystem.backend.model.security.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    public void editUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    public User getUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,username);
    }

    public Boolean isUserExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, username) != null;
    }
}
