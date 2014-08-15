package uk.co.resilientdatasystems.thtkya.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;

@Configuration
@DependsOn({ "dataSource" })
@Profile("sampleData")
public class SampleDataPopulator {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void populateSampleData() throws ScriptException, SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("quotes.sql"));
        populator.addScript(new ClassPathResource("stats.sql"));

        populator.populate(dataSource.getConnection());
    }
}
