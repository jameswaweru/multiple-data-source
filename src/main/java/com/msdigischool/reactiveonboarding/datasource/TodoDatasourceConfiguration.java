package com.msdigischool.reactiveonboarding.datasource;

import com.msdigischool.reactiveonboarding.datalayer.todos.entity.Todo;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;


@Configuration
public class TodoDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.todos")
    public DataSourceProperties todosDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource todosDataSource() {
        return todosDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Bean
    public JdbcTemplate todosJdbcTemplate(@Qualifier("todosDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
