package atcons.services;

import atcons.dao.impl.DataDAO;
import atcons.dao.impl.DataSourceDAO;
import atcons.model.Data;
import atcons.model.DataSource;
import atcons.util.exceptions.DataSourceNotFoundException;
import atcons.util.exceptions.InvalidDataSourceException;
import org.hibernate.LockMode;
import org.hibernate.annotations.OptimisticLock;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DataSourceService {

    @Inject
    private DataSourceDAO dataSourceDAO;

    @Inject
    private DataDAO dataDAO;

    public List<DataSource> listAllDataSources() {
        return dataSourceDAO.getAll();
    }

    public DataSource getDataSource(long id) {
        return dataSourceDAO.getById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DataSource addDataSource(DataSource dataSource) throws InvalidDataSourceException {
        if (dataSource.getUrl() == null || dataSource.getType() == null) throw new InvalidDataSourceException();
        return dataSourceDAO.create(dataSource);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeDataSource(long id) throws DataSourceNotFoundException {
        DataSource dataSource = dataSourceDAO.getById(id);
        System.out.println("REMOVE DATA SOURCE WITH ID=" + id);
        if (dataSource == null) {
            System.out.println("DATA SOURCE WITH ID=" + id + " NOT FOUND");
            throw new DataSourceNotFoundException(id + "");
        } else {
            Data data = dataDAO.findBySource(dataSource);
            if (data != null) {
                dataDAO.remove(data);
            }
            dataSourceDAO.remove(dataSource);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateDataSource(DataSource dataSource) throws DataSourceNotFoundException {
        DataSource updatableDataSource = dataSourceDAO.getById(dataSource.getId());
        if (updatableDataSource == null) {
            throw new DataSourceNotFoundException(dataSource.getId() + "");
        } else {
            dataSourceDAO.update(dataSource);
        }
    }
}
