package com.smiddle.core.dao.impl;

import com.smiddle.core.dao.CallDAO;
import com.smiddle.core.model.Call;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CallDAOImpl implements CallDAO {
    @PersistenceContext(unitName = "NewEntityManagerFactory")
    private EntityManager em;

    @Override
    @Transactional
    public void saveCall(Call call) {
        em.merge(call);
    }
}
