/*
 * Created by Daniel Marell 14-02-22 13:51
 */
package se.caglabs.doodleshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import se.caglabs.doodleshop.util.Config;
import se.caglabs.doodleshop.util.DBMS;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
public class Application {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        start(args);
    }

    public static void start(String[] args) {
        DBMS.INSTANCE.migrate();
        applicationContext = SpringApplication.run(Application.class, args);
    }

    public static void start() {
        start(new String[0]);
    }

    public static void stop() {
        if (applicationContext != null) {
            applicationContext.stop();
            applicationContext = null;
        }
    }

//    @Bean
//    public ManagementServerProperties managementServerProperties() {
//        ManagementServerProperties props = new ManagementServerProperties();
//        props.setPort(8576);
//        return props;
//    }

//    @Bean
//    public ManagementSecurityAutoConfiguration managementServerConf() {
//        ManagementSecurityAutoConfiguration c = new ManagementSecurityAutoConfiguration();
//        ManagementServerProperties props = new ManagementServerProperties();
//        props.setPort(8576);
//        return props;
//    }

    @Bean
    public EmbeddedServletContainerFactory containerFactory() {
        return new TomcatEmbeddedServletContainerFactory(Config.INSTANCE.getHttpPort());
    }

    @Bean
    public DataSource dataSource() {
        return DBMS.INSTANCE.getDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("se.caglabs.doodleshop");
        lef.setJpaProperties(getJpaProperties());
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    private Properties getJpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
            }
        };
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
