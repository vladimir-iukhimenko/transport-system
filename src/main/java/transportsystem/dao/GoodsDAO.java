package transportsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportsystem.model.Goods;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Repository
public class GoodsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory};

    public void add(Goods goods) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(goods);
    }

    public void delete(Goods goods) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(goods);
    }

    public void edit(Goods goods) {
        Session session = sessionFactory.getCurrentSession();
        session.update(goods);
    }

    public Goods getGoodsById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Goods.class,id);
    }

    @SuppressWarnings("unchecked")
    public List<Goods> getAllGoods() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Goods").list();
    }
}
