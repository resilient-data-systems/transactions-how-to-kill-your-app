package uk.co.resilientdatasystems.thtkya.testutils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("test")
@EnableTransactionManagement
public class TestOnlyDataSourceConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
        bean.setDatabaseType(EmbeddedDatabaseType.H2);
        bean.setDatabaseName("thtkya");
        bean.afterPropertiesSet();
        DataSource dataSource = bean.getObject();

        createSchema(dataSource.getConnection());

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() throws PropertyVetoException, SQLException {
        return new NamedParameterJdbcTemplate(dataSource());
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
