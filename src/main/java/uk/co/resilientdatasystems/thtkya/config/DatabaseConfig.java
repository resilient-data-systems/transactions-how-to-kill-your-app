package uk.co.resilientdatasystems.thtkya.config;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final String jdbcUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=true";
    private final String driverClass = "org.h2.Driver";

    private final int maxPoolSize = 6;
    private final int minPoolSize = 0;
    private final int acquireIncrement = 1;
    private final int unreturnedConnectionTimeout = 30;

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() throws PropertyVetoException, SQLException {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException, SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClass(driverClass);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setUnreturnedConnectionTimeout(unreturnedConnectionTimeout);

        createSchema(dataSource.getConnection());

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException, SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    private void createSchema(Connection connection){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.populate(connection);
    }


}