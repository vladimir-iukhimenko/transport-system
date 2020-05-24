package com.transportsystem.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.Engine;

import java.util.List;

/**
 * Created by DZCKJS0 on 28.11.2019.
 */
@Repository
public class EngineDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    public void addEngine(Engine engine) {
        Session session = sessionFactory.getCurrentSession();
        session.save(engine);
    }

    public void deleteEngine(Engine engine) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(engine);
    }

    public void editEngine(Engine engine) {
        Session session = sessionFactory.getCurrentSession();
        session.update(engine);
    }

    public Engine getEngineById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Engine.class,id);
    }

    @SuppressWarnings("unchecked")
    public List<Engine> getAllEngines() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Engine").list();
    }
}
