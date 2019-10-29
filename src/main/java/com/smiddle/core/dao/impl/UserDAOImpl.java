package com.smiddle.core.dao.impl;

import com.smiddle.core.dao.UserDAO;
import com.smiddle.core.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "NewEntityManagerFactory")
    private EntityManager em;

    @Override
    @Transactional
    public void saveUser(User user) {
        try {
            em.merge(user);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return em.createQuery("SELECT COUNT(u) FROM OldUser u", Long.class).getSingleResult();
    }
}
