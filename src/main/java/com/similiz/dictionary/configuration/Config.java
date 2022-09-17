package com.similiz.dictionary.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.similiz.dictionary")
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource({
        "classpath:application.properties",
        "classpath:logging.properties"
})
public class Config {

    @Autowired
    private Environment environment;
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        try {
            pool.setDriverClass("com.mysql.cj.jdbc.Driver");
            pool.setJdbcUrl(environment.getProperty("db.jdbcURL"));
            pool.setUser(environment.getProperty("db.login"));
            pool.setPassword(environment.getProperty("db.password"));
//            pool.setJdbcUrl("jdbc:mysql://localhost:3306/dictionary?allowPublicKeyRetrieval=true&useSSL=false");
//            pool.setUser("bestuser");
//            pool.setPassword("bestuser");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return pool;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.similiz.dictionary.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
