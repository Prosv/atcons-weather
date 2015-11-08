package atcons.dao;

import atcons.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Abstract class providing basic operations with various entities
 * in data access layer
 *
 * @param <T> entity type
 */
public abstract class AbstractDAO<T extends BaseEntity> implements GenericDAO<T> {

    protected Class entityClass;

    /**
     * Default constructor with input class definition
     */
    protected AbstractDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Abstract getter for standard persistence entity manager
     *
     * @return entity manager
     */
    public abstract EntityManager getEntityManager();

    /**
     * Create a database record for given entity
     * @param t entity object one need to insert to database
     * @return recorded entity
     */
    @Override
    public T create(T t) {
        getEntityManager().persist(t);
        return t;
    }

    /**
     * Get database record for given ID
     * @param id ID of requested object
     * @return recorded object, or <code>null</code> if not found
     */
    @Override
    public T getById(Long id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    /**
     * Update database record
     * @param t entity object one need to update in database
     */
    @Override
    public void update(T t) {
        getEntityManager().merge(t);
    }

    /**
     * Remove record from database
     * @param t entity object one need to remove from database
     */
    @Override
    public void remove(T t) {
        t = getEntityManager().merge(t);
        getEntityManager().remove(t);
    }

    /**
     * Get list of all records in table database
     * @return list of all database records of type T
     */
    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> tRoot = query.from(entityClass);
        return getEntityManager().createQuery(query.select(tRoot)).getResultList();
    }
}
