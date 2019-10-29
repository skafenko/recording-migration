package com.smiddle.core.dao.old.impl;

import com.smiddle.core.dao.old.OldCallDAO;
import com.smiddle.core.model.old.OldCall;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class OldCallDAOImpl implements OldCallDAO {

    @PersistenceContext(unitName = "OldEntityManagerFactory")
    private EntityManager em;

    @Override
    public List<OldCall> getAllOldCalls(Date startDate, Date finishDate, int maxResult, int startPosition) {
        return em.createQuery("SELECT c FROM OldCall c " +
                "where c.dateStart >= :startDate and c.dateStart < :finishDate order by c.id", OldCall.class)
                .setParameter("startDate", startDate)
                .setParameter("finishDate", finishDate)
                .setMaxResults(maxResult)
                .setFirstResult(startPosition)
                .getResultList();
    }

    public long getCount(Date startDate, Date finishDate) {
        return em.createQuery("SELECT COUNT(c) FROM OldCall c " +
                "where c.dateStart >= :startDate and c.dateStart < :finishDate", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("finishDate", finishDate)
                .getSingleResult();
    }
}
