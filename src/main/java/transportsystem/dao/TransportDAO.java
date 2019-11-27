package transportsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportsystem.model.Engine;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;
import java.util.*;

/**
 * Created by Владимир on 09.11.2019.
 */
@Repository
public class TransportDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTransport(Transport transport) {
        Session session = sessionFactory.getCurrentSession();
        session.save(transport);
    }

    public void deleteTransport(Transport transport) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transport);
    }

    public void editTransport(Transport transport) {
        Session session = sessionFactory.getCurrentSession();
        session.update(transport);

    }

    public Transport getTransportById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Transport.class,id);
    }


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
    public List<Transport> getAllTransports() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Transport").list();
    }


    @SuppressWarnings("unchecked")
    public List<Engine> getAllEngines() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Engine").list();
    }
}
