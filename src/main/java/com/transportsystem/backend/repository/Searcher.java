package com.transportsystem.backend.repository;

import jakarta.persistence.EntityManagerFactory;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Searcher {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List search(String text, Class object, String... fields) {
        var sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(object).get();
        Query query = queryBuilder.keyword().onFields(fields).matching(text).createQuery();
        FullTextQuery jpaQuery = fullTextSession.createFullTextQuery(query, object);
        List resultList = jpaQuery.getResultList();
        return resultList;
    }
}
