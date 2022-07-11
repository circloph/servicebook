// package net.thumbtack.servicebook.config;

// import javax.sql.DataSource;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.jdbc.datasource.DriverManagerDataSource;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Configuration
// @EnableJpaRepositories(basePackages = "net.thumbtack.servicebook.repository")

// @EnableTransactionManagement
// public class ConfigH2 {

//     @Bean
//     @Profile("test")
//         DataSource dataSource() {
//         DriverManagerDataSource dataSource = new DriverManagerDataSource();
//         dataSource.setDriverClassName("org.h2.Driver");
//         dataSource.setUrl("jdbc:h2:mem:servicebook;DB_CLOSE_DELAY=-1");
//         dataSource.setUsername("test");
//         dataSource.setPassword("test");

//         return dataSource;
//     }

// //     @Bean
// //     public EntityManagerFactory entityManagerFactory() {

// //     HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
// //     vendorAdapter.setGenerateDdl(true);

// //     LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
// //     factory.setJpaVendorAdapter(vendorAdapter);
// //     factory.setPackagesToScan("net.thumbtack.servicebook.repository");
// //     factory.setDataSource(dataSource());
// //     factory.afterPropertiesSet();

// //     return factory.getObject();
// //   }
    
// }
    
