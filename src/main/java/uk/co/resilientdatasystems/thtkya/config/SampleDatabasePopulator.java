package uk.co.resilientdatasystems.thtkya.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Component;

@Component
public class SampleDatabasePopulator {
    private final static Logger log = LoggerFactory.getLogger(SampleDatabasePopulator.class);

    private final DataSource dataSource;

    @Autowired
    public SampleDatabasePopulator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void populateData() throws ScriptException, SQLException {

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("quotes.sql"));
        populator.addScript(new ClassPathResource("stats.sql"));

        populator.populate(dataSource.getConnection());

        log.info("Populated DB with sample data");
    }
}
