package com.smiddle.core.dao.impl;

import com.smiddle.core.dao.GroupDAO;
import com.smiddle.core.model.Group;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GroupDAOImpl implements GroupDAO {

    @PersistenceContext(unitName = "NewEntityManagerFactory")
    private EntityManager em;

    @Override
    @Transactional
    public void saveGroup(Group group) {
        try {
            em.merge(group);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return em.createQuery("SELECT COUNT(u) FROM Group u", Long.class).getSingleResult();
    }
}
