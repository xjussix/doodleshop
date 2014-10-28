/*
 * Created by Daniel Marell 14-02-22 13:51
 */
package se.caglabs.doodleshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import se.caglabs.doodleshop.util.BuildInfo;
import se.caglabs.doodleshop.util.RunEnvironment;

import javax.servlet.ServletContextListener;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        String runEnv = System.getProperty("se.caglabs.doodleshop.environment");
        if (runEnv == null) {
            runEnv = "local";
        }
        app.setAdditionalProfiles(runEnv);
        app.addListeners(new ApplicationListener<ApplicationEnvironmentPreparedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
                logger.info("AppVersion: " + BuildInfo.getAppVersion());
                logger.info("RunEnvironment: " + RunEnvironment.getCurrentEnvironment(event.getEnvironment()));
            }
        });
        app.run(args);
    }

    @Bean
    public ServletContextListener loggerInitializer() {
        return new LogbackLoggerInitializer();
    }
}
