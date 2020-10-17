package br.com.precredenciamento.config.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfig {
	
    @Bean(name = "dataSourcePreCredenciamento")
    @Primary
    public DataSource preCredenciamentoDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		dataSource.setUrl("jdbc:sqlserver://precredenciamento-sqlserver:1433;databaseName=master");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("yourStrong(!)Password");
		
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=master");
		dataSource.setUsername("sa");
		dataSource.setPassword("SA_PASSWORD=yourStrong(!)Password");

		return dataSource;
    }
    
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.SQL_SERVER);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(preCredenciamentoDataSource());
		em.setPackagesToScan("br.com.precredenciamento.model");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
//		properties.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.SQLServerDialect");
//		properties.setProperty("hibernate.transaction.flush_before_completion", "true");
		properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
		return properties;
	}
	
	@Bean("dataSourceSMA")
	public DataSource sma() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://s2-sql-conf:1433;databaseName=DBSISTEMAS");
		dataSource.setUsername("UserPreCred");
		dataSource.setPassword("[6>tOWK,");
		return dataSource;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(sma());
    }
	
}
