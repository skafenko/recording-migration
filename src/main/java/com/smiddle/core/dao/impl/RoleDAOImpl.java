package com.smiddle.core.dao.impl;

import com.smiddle.core.dao.RoleDAO;
import com.smiddle.core.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext(unitName = "NewEntityManagerFactory")
    private EntityManager em;

    @Override
    @Transactional
    public void saveAllRoles(Role role) {
        try {
            em.merge(role);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return em.createQuery("SELECT COUNT(u) FROM Role u", Long.class).getSingleResult();
    }
}
