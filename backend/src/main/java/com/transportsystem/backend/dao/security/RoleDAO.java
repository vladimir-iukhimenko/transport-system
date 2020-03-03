package com.transportsystem.backend.dao.security;

import com.transportsystem.backend.model.security.ERole;
import com.transportsystem.backend.model.security.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    public Role getRoleByName(ERole name) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class,name);
    }

}
