package uk.co.resilientdatasystems.thtkya.testutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles({"test"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistanceTestBase.TestConfig.class})
public abstract class PersistanceTestBase {

    @Autowired
    DataSource dataSource;

    @Before
    public void before() throws SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("clean-db.sql"));
        populator.addScript(new ClassPathResource("test-data.sql"));

        Connection connection = dataSource.getConnection();
        populator.populate(connection);
    }


    @Configuration
    @ComponentScan(basePackageClasses = TestOnlyDataSourceConfig.class)
    public static class TestConfig {

    }
}
