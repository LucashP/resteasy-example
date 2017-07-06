package pl.lucash.resteasy.infrastructure;

import org.dozer.DozerBeanMapper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@Singleton
@Startup
@ApplicationScoped
public class AppConfig {

    private DozerBeanMapper dozerBeanMapper;
    private DataSource dataSource;

    public AppConfig() {
    }

    @Inject
    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dozerBeanMapper = new DozerBeanMapper();
    }

    @PostConstruct
    public void init() {
        ArrayList<String> mappings = new ArrayList<>();
        mappings.add("mappings.xml");
        this.dozerBeanMapper.setMappingFiles(mappings);
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
