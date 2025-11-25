package threadsafe.exemplo5.dataconfig;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "threadsafe.exemplo5.repository.bancoa",
    entityManagerFactoryRef = "entityManagerFactoryA",
    transactionManagerRef = "transactionManagerA"
)
public class DataSourceAConfig {

    @Primary
    @Bean(name = "dataSourceA")
    @ConfigurationProperties(prefix = "spring.datasource.a")
    public DataSource dataSourceA() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryA")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryA(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceA") DataSource dataSource) {

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", "true");

        return builder
                .dataSource(dataSource)
                .packages("threadsafe.exemplo5.entity.bancoa")
                .persistenceUnit("bancoA")
                .properties(props)
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerA")
    public PlatformTransactionManager transactionManagerA(
            @Qualifier("entityManagerFactoryA") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
