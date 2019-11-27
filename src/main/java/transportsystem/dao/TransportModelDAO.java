package transportsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportsystem.model.TransportModel;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Repository
public class TransportModelDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTransportModel(TransportModel transportModel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(transportModel);
    }

    public void deleteTransportModel(TransportModel transportModel) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transportModel);
    }

    public void editTransportModel(TransportModel transportModel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(transportModel);
    }

    public TransportModel getTransportModelById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TransportModel.class,id);
    }

    @SuppressWarnings("unchecked")
    public List<TransportModel> getAllTransportModels() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TransportModel").list();
    }

}
