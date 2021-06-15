package cn.beecp.starter.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDs1",
        transactionManagerRef = "transactionManagerDs1",
        basePackages = {"cn.beecp.starter.demo.entity1"})
public class Ds1JpaConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("ds1")
    private DataSource ds1;

    @Primary
    @Bean(name = "transactionManagerDs1")
    public PlatformTransactionManager transactionManagerDs1(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryDs1(builder).getObject());
    }

    @Primary
    @Bean(name = "entityManagerDs1")
    public EntityManager entityManagerDs1(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryDs1(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryDs1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDs1(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ds1)
                .properties(getVendorProperties(ds1))
                .packages("cn.beecp.starter.demo.entity1")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
}
