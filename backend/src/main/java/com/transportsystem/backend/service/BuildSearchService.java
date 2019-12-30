package com.transportsystem.backend.service;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@Service
public class BuildSearchService implements ApplicationListener<ApplicationReadyEvent> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
         try {
            FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
            fullTextSession.createIndexer().startAndWait();
        }
        catch (InterruptedException e) {
            System.out.println(
                   "An error occurred trying to build the search index: " +
                           e.toString());
       }
        return;
    }
}
