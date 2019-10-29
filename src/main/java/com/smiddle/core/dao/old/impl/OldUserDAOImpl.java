package com.smiddle.core.dao.old.impl;

import com.smiddle.core.dao.old.OldUserDAO;
import com.smiddle.core.model.old.OldUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class OldUserDAOImpl implements OldUserDAO {

    @PersistenceContext(unitName = "OldEntityManagerFactory")
    private EntityManager em;

    @Override
    public List<OldUser> getAllOldUsers(int limit, int offset) {
        return em.createQuery("SELECT r FROM OldUser r order by r.id", OldUser.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long getCount() {
        return em.createQuery("SELECT COUNT(u) FROM OldUser u", Long.class).getSingleResult();
    }
}
