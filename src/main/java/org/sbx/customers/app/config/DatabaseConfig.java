/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sbx.customers.app.config;

import java.util.Properties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.sbx.customers.service", "org.sbx.customers.dao"})
@PropertySource(value = {"classpath:application.properties"})
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    private static final String JDBC_DRIVER;
    private static final String JDBC_URL;
    private static final String JDBC_USERNAME;
    private static final String JDBC_PASSWORD;

    private static final String HIBERNATE_DIALECT;
    private static final String HIBERNATE_HBM2DDL_AUTO;
    private static final String HIBERNATE_SHOW_SQL;
    private static final String HIBERNATE_FORMAT_SQL;

    static {
        JDBC_DRIVER = "jdbc.driver";
        JDBC_URL = "jdbc.url";
        JDBC_USERNAME = "jdbc.username";
        JDBC_PASSWORD = "jdbc.password";

        HIBERNATE_DIALECT = "hibernate.dialect";
        HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
        HIBERNATE_SHOW_SQL = "hibernate.show_sql";
        HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    }

    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(JDBC_DRIVER));
        dataSource.setUrl(environment.getProperty(JDBC_URL));
        dataSource.setUsername(environment.getProperty(JDBC_USERNAME));
        dataSource.setPassword(environment.getProperty(JDBC_PASSWORD));
        return dataSource;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) throws NamingException {

        JpaTransactionManager jpaTransaction = new JpaTransactionManager();
        jpaTransaction.setEntityManagerFactory(emf);

        return jpaTransaction;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEmf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());
        emf.setPersistenceUnitName("spring-jpa-unit");
        emf.setJpaVendorAdapter(getHibernateAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(HIBERNATE_DIALECT, environment.getProperty(HIBERNATE_DIALECT));
        jpaProperties.put(HIBERNATE_HBM2DDL_AUTO, environment.getProperty(HIBERNATE_HBM2DDL_AUTO));
        jpaProperties.put(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
        jpaProperties.put(HIBERNATE_FORMAT_SQL, environment.getProperty(HIBERNATE_FORMAT_SQL));

        emf.setJpaProperties(jpaProperties);

        return emf;
    }

    @Bean
    public JpaVendorAdapter getHibernateAdapter() {
        return new HibernateJpaVendorAdapter();
    }

}
