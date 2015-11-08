package atcons.dao;

import atcons.model.BaseEntity;

import java.util.List;

/**
 * This interface provides basic common operations for
 * <a href="en.wikipedia.org/wiki/Data_access">data access layer</a>
 *
 * @param <T>
 */

public interface GenericDAO<T extends BaseEntity> {

    /**
     * Create a database record for given entity
     * @param t entity object one need to insert to database
     * @return recorded entity
     */
    T create(T t);

    /**
     * Get database record for given ID
     * @param id ID of requested object
     * @return recorded object, or <code>null</code> if not found
     */
    T getById(Long id);

    /**
     * Update database record
     * @param t entity object one need to update in database
     */
    void update(T t);

    /**
     * Remove record from database
     * @param t entity object one need to remove from database
     */
    void remove(T t);

    /**
     * Get list of all records in table database
     * @return list of all database records of type T
     */
    List<T> getAll();

}
