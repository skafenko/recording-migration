package com.smiddle.core.dao.old.impl;

import com.smiddle.core.dao.old.OldCallDAO;
import com.smiddle.core.model.old.OldCall;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class OldCallDAOImpl implements OldCallDAO {

    @PersistenceContext(unitName = "OldEntityManagerFactory")
    private EntityManager em;

    @Override
    public List<OldCall> getAllOldCalls(int maxResult, int startPosition) {
        return em.createQuery("SELECT c FROM OldCall c", OldCall.class)
                .setMaxResults(maxResult)
                .setFirstResult(startPosition)
                .getResultList();
    }

    public long getCount() {
        return em.createQuery("SELECT COUNT(c) FROM OldCall c", Long.class).getSingleResult();
    }
}
