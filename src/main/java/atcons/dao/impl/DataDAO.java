package atcons.dao.impl;

import atcons.dao.AbstractDAO;
import atcons.model.Data;
import atcons.model.DataSource;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Named(value = "data_dao")
public class DataDAO extends AbstractDAO<Data> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Data findBySource(DataSource source) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Data> query = criteriaBuilder.createQuery(Data.class);
        Root<Data> root = query.from(Data.class);
        query.select(root).where(criteriaBuilder.equal(root.get("source"), source));
        List<Data> dataList = entityManager.createQuery(query).getResultList();
        if (dataList.size() == 0) return null;
        else return dataList.get(0);
    }
}
