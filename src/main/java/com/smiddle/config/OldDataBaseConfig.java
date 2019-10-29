package com.smiddle.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = "com.smiddle")
public class OldDataBaseConfig {

    @Bean("OldProperties")
    @ConfigurationProperties(prefix = "old.spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "OldDataSource")
    public DataSource dataSource(@Qualifier("OldProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("OldJdbcTemplate")
    public JdbcTemplate newJdbcTemplate(@Qualifier("OldDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("OldParamJdbcTemplate")
    public NamedParameterJdbcTemplate cmNamedJdbcTemplate(@Qualifier("OldDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "OldEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("OldDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .persistenceUnit("OldEntityManagerFactory")
                .packages("com.smiddle.core.model")
                .build();
    }

    @Bean("OldTransactionDataSource")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("OldDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "OldTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("OldEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
