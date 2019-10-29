package com.smiddle.core.dao.old.impl;

import com.smiddle.core.dao.old.OldRoleDAO;
import com.smiddle.core.model.old.OldRole;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class OldRoleDAOImpl implements OldRoleDAO {

    @PersistenceContext(unitName = "OldEntityManagerFactory")
    private EntityManager em;

    @Override
    public List<OldRole> getAllOldRoles() {
        return em.createQuery("SELECT r FROM OldRole r", OldRole.class).getResultList();
    }
}
