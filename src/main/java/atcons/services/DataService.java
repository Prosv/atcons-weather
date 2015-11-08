package atcons.services;

import atcons.dao.impl.DataDAO;
import atcons.model.Data;
import atcons.model.DataSource;
import atcons.util.exceptions.DataNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Stateless
public class DataService {

    @Inject
    private DataDAO dataDAO;

    @EJB
    private DataSourceService dataSourceService;

    @EJB
    private ServiceUtils utils;

    public List<Data> listAllData() {
        return dataDAO.getAll();
    }

    public Data getData(long id) {
        Data data = dataDAO.getById(id);
        return data;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Data addData(Data data) {
        return dataDAO.create(data);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Data updateData(Data data) throws DataNotFoundException, IOException {
        Data realData = dataDAO.getById(data.getId());
        if (realData == null) {
            throw new DataNotFoundException(data.getId() + "");
        } else {
            DataSource source = realData.getSource();
            if (source == null) return realData;
            if (dataSourceService.getDataSource(source.getId()) == null) return realData;
            realData.setData(utils.getContentFromResource(source.getUrl()));
            realData.setLast_update(new Timestamp(new Date().getTime()));
            dataDAO.update(realData);
            return realData;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAll() throws IOException, DataNotFoundException {
//        List<Data> datalist = listAllData();
//        for (Iterator<Data> iterator = datalist.iterator(); iterator.hasNext(); ) {
//            Data data = iterator.next();
//            updateData(data);
//        }
        List<DataSource> dataSources = dataSourceService.listAllDataSources();
        for (Iterator<DataSource> iterator = dataSources.iterator(); iterator.hasNext(); ) {
            DataSource source = iterator.next();
            Data data = dataDAO.findBySource(source);
            if (data == null) {
                data = new Data();
                data.setSource(source);
                data.setData(utils.getContentFromResource(source.getUrl()));
                data.setLast_update(new Timestamp(new Date().getTime()));
                addData(data);
            } else {
                updateData(data);
            }
        }
    }
}
