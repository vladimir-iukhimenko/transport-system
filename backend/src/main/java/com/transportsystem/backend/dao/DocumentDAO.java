package com.transportsystem.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.Document;
import com.transportsystem.backend.model.DocumentEmployee;
import com.transportsystem.backend.model.DocumentTransport;

import java.util.List;

/**
 * Created by DZCKJS0 on 18.11.2019.
 */
@Repository
public class DocumentDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.save(document);
    }

    public void delete(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(document);
    }

    public Document getDocumentTransportById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DocumentTransport.class,id);
    }

    public Document getDocumentEmployeeById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DocumentEmployee.class,id);
    }

    public void edit(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.update(document);
    }

    @SuppressWarnings("unchecked")
    public List<Document> getAllEmployeeDocuments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DocumentEmployee").list();
    }

    @SuppressWarnings("unchecked")
    public List<Document> getAllTransportDocuments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DocumentTransport").list();
    }
}
