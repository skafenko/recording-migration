package com.smiddle.core.dao.old.impl;

import com.smiddle.core.dao.old.OldGroupDAO;
import com.smiddle.core.model.old.OldGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OldGroupDAOImpl implements OldGroupDAO {

    @PersistenceContext(unitName = "OldEntityManagerFactory")
    private EntityManager em;

    @Override
    public List<OldGroup> getAllOldGroupsWithParentNull() {
        return em.createQuery("SELECT c FROM OldGroup c where c.parent is null", OldGroup.class).getResultList();
    }
}
