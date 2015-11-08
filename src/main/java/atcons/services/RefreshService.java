package atcons.services;

import atcons.util.exceptions.DataNotFoundException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
@Startup
public class RefreshService {

    @EJB
    private DataService dataService;

    @Resource
    private TimerService timerService;

    private long refreshInterval;

    public long getRefreshInterval() {
        return refreshInterval;
    }

    public void updateProperties() {
        Properties prop = new Properties();
        InputStream inputStream = null;

        try {
            //TODO: use resource instead of file
            inputStream = new FileInputStream("appconfig/timer.properties");
            prop.load(inputStream);
            refreshInterval = Integer.parseInt(prop.getProperty("refresh.interval"));
        } catch (FileNotFoundException nfe) {
            refreshInterval = 5000L;
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @PostConstruct
    public void init() {
        updateProperties();
        timerService.createIntervalTimer(refreshInterval,
                refreshInterval, new TimerConfig(null, false));
    }

    @Timeout
    public void process(Timer timer) throws IOException, DataNotFoundException {
        doRefresh();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void doRefresh() throws IOException, DataNotFoundException {
        dataService.updateAll();
    }

}
