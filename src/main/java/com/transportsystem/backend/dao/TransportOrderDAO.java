package com.transportsystem.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.TransportOrder;

import java.util.List;

/**
 * Created by DZCKJS0 on 02.12.2019.
 */
@Repository
public class TransportOrderDAO {
    private SessionFactory sessionfactory;

    @Autowired
    public void setSessionfactory(SessionFactory sessionfactory) {this.sessionfactory = sessionfactory;}

    public void addTransportOrder(TransportOrder transportorder) {
        Session session = sessionfactory.getCurrentSession();
        session.save(transportorder);
    }

    public void deleteTransportOrder(TransportOrder transportorder) {
        Session session = sessionfactory.getCurrentSession();
        session.delete(transportorder);
    }

    public void editTransportOrder(TransportOrder transportorder) {
        Session session = sessionfactory.getCurrentSession();
        session.update(transportorder);
    }

    public TransportOrder getTransportOrderById(int id) {
        Session session = sessionfactory.getCurrentSession();
        return session.get(TransportOrder.class,id);
    }

    @SuppressWarnings("unchecked")
    public List<TransportOrder> getAllTransportOrders() {
        Session session = sessionfactory.getCurrentSession();
        return session.createQuery("from TransportOrder").list();
    }
}
