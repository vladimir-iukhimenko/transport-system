package transportsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportsystem.model.Nomenclature;

import java.util.List;


/**
 * Created by Владимир on 29.11.2019.
 */
@Repository
public class NomenclatureDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    public void add(Nomenclature nomenclature) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(nomenclature);
    }

    public void delete(Nomenclature nomenclature) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(nomenclature);
    }

    public void edit(Nomenclature nomenclature) {
        Session session = sessionFactory.getCurrentSession();
        session.update(nomenclature);
    }

    public Nomenclature getNomenclatureById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.get(Nomenclature.class,id);
    }

    @SuppressWarnings("unchecked")
    public List<Nomenclature> getAllNomenclatures() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Nomenclature").list();
    }
}
