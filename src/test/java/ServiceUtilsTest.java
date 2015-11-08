import atcons.dao.AbstractDAO;
import atcons.dao.impl.DataSourceDAO;
import atcons.model.DataSource;
import atcons.services.DataService;
import atcons.services.DataSourceService;
import atcons.services.ServiceUtils;
import atcons.util.exceptions.DataSourceNotFoundException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ServiceUtilsTest {

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @EJB
    private DataSourceService dataSourceService;

    @EJB
    private DataService dataService;

    @EJB
    private ServiceUtils serviceUtils;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(DataSource.class.getPackage())
                .addPackage(DataService.class.getPackage())
                .addPackage(DataSourceNotFoundException.class.getPackage())
                .addPackage(DataSourceDAO.class.getPackage())
                .addPackage(AbstractDAO.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void preparePersistenceTest() throws Exception {
        insertTestData();
        startTransaction();
    }

    private void insertTestData() {

    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void finalizeTest() throws Exception {
        endTransaction();
    }

    private void endTransaction() throws Exception {
        utx.commit();
        clear();
    }

    private void clear() throws Exception {
        utx.begin();
//        em.createNativeQuery("set foreign_key_checks=0").executeUpdate();
        em.createNativeQuery("truncate data").executeUpdate();
        em.createNativeQuery("truncate data_source").executeUpdate();
//        em.createNativeQuery("set foreign_key_checks=1").executeUpdate();
        utx.commit();
    }

    //BEGIN TESTS:


    @Test
    public void testServiceUtils_getContent() throws Exception {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=";
        assertEquals("{\"message\":\"Error: Not found city\",\"cod\":\"404\"}",
                     serviceUtils.getContentFromResource(url));

    }
}
