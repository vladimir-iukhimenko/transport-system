package com.transportsystem.backend.dao.security;

import com.transportsystem.backend.model.security.ERole;
import com.transportsystem.backend.model.security.Role;
import com.transportsystem.backend.model.security.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    public Role getRoleByName(ERole name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = criteriaBuilder.createQuery(Role.class);
        Root<Role> from = criteria.from(Role.class);
        criteria.select(from);
        criteria.where(criteriaBuilder.equal(from.get("name"), name));
        TypedQuery<Role> typedQuery = session.createQuery(criteria);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

}
