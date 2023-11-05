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
        entityManagerFactoryRef = "onBoardingdataEntityManagerFactory",
        transactionManagerRef = "onBoardingDataTransactionManager",
        basePackages = {
                "com.msdigischool.reactiveonboarding.datalayer.onboarding.entity",
                "com.msdigischool.reactiveonboarding.datalayer.onboarding.repository"
        })
public class OnboardingDataSource {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.onboarding")
    public DataSourceProperties onboardingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource OnboardingDataSource() {
        return onboardingDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }


    @Bean
    public JdbcTemplate onBoardingJdbcTemplate(@Qualifier("OnboardingDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean onBoardingdataEntityManagerFactory(
            @Qualifier("OnboardingDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.msdigischool.reactiveonboarding.datalayer.onboarding.entity",
                        "com.msdigischool.reactiveonboarding.datalayer.onboarding.repository"
                )
                .build();
    }

    @Bean
    public PlatformTransactionManager onBoardingDataTransactionManager(
            @Qualifier("onBoardingdataEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }


}
