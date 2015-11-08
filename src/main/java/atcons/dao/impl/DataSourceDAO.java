package atcons.dao.impl;

import atcons.dao.AbstractDAO;
import atcons.model.DataSource;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "ds_dao")
public class DataSourceDAO extends AbstractDAO<DataSource> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
