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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "threadsafe.exemplo5.repository.bancob",
    entityManagerFactoryRef = "entityManagerFactoryB",
    transactionManagerRef = "transactionManagerB"
)
public class DataSourceBConfig {

    @Bean(name = "dataSourceB")
    @ConfigurationProperties(prefix = "spring.datasource.b")
    public DataSource dataSourceB() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryB")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryB(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceB") DataSource dataSource) {

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", "true");

        return builder
                .dataSource(dataSource)
                .packages("threadsafe.exemplo5.entity.bancob")
                .persistenceUnit("bancoB")
                .properties(props)
                .build();
    }

    @Bean(name = "transactionManagerB")
    public PlatformTransactionManager transactionManagerB(
            @Qualifier("entityManagerFactoryB") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
