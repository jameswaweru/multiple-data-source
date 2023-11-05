package com.msdigischool.reactiveonboarding.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "dataEntityManagerFactory",
        transactionManagerRef = "dataTransactionManager",
        basePackages = {
                "com.msdigischool.reactiveonboarding.datalayer.communication.entity",
                "com.msdigischool.reactiveonboarding.datalayer.communication.repository"
        })
public class CommunicationDataSource {

        @Bean
        @ConfigurationProperties("spring.datasource.communication")
        public DataSourceProperties dataSourceProperties() {
                return new DataSourceProperties();
        }

        @Bean
        public DataSource CommunicationDataSource() {
                return dataSourceProperties()
                        .initializeDataSourceBuilder()
                        .build();
        }


        @Bean
        public JdbcTemplate communicationJdbcTemplate(@Qualifier("CommunicationDataSource") DataSource dataSource) {
                return new JdbcTemplate(dataSource);
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean dataEntityManagerFactory(
                @Qualifier("CommunicationDataSource") DataSource dataSource,
                EntityManagerFactoryBuilder builder) {
                return builder
                        .dataSource(dataSource)
                        .packages(
                                "com.msdigischool.reactiveonboarding.datalayer.communication.entity",
                                "com.msdigischool.reactiveonboarding.datalayer.communication.repository"
                        )
                        .build();
        }

        @Bean
        public PlatformTransactionManager dataTransactionManager(
                @Qualifier("dataEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
                return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
        }

        @Bean
        public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
                return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
        }
}
