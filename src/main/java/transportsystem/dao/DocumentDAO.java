package transportsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportsystem.model.Document;

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
        session.persist(document);
    }

    public void delete(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(document);
    }

    public Document getDocumentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Document.class,id);
    }

    public void edit(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.update(document);
    }

    public List<Document> getAllEmployeeDocuments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DocumentEmployee").list();
    }

    public List<Document> getAllTransportDocuments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DocumentTransport").list();
    }
}
