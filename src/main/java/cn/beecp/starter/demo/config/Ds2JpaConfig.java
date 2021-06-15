package cn.beecp.starter.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "entityManagerFactoryDs2",
        transactionManagerRef = "transactionManagerDs2",
        basePackages = {"cn.beecp.starter.demo.entity2"})
public class Ds2JpaConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("ds2")
    private DataSource ds2;

    @Bean(name = "transactionManagerDs2")
    public PlatformTransactionManager transactionManagerDs2(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryDs2(builder).getObject());
    }

    @Bean(name = "entityManagerDs2")
    public EntityManager entityManagerDs2(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryDs2(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryDs2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDs2(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ds2)
                .properties(getVendorProperties(ds2))
                .packages("cn.beecp.starter.demo.entity2")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
}
