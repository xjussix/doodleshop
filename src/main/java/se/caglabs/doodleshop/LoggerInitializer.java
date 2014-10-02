/*
 * Created by Daniel Marell 14-10-01 22:14
 */
package se.caglabs.doodleshop;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import se.caglabs.doodleshop.util.Environment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.MissingResourceException;

/**
 * Listener that configures the log context reading logback configuration from
 * a logback config file per environment:
 * LOCAL.logback.xml
 * PROD.logback.xml
 * etc.
 */
public class LoggerInitializer implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(LoggerInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext) {
            // SLF4J is bound to logback in the current environment
            LoggerContext context = (LoggerContext) loggerFactory;
            String logbackConfigResourceKey = Environment.getCurrentEnvironment() + "." + ContextInitializer.AUTOCONFIG_FILE;
            try {
                Resource logbackConfigResource = new DefaultResourceLoader().getResource(logbackConfigResourceKey);
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(context);
                context.reset();
                configurator.doConfigure(logbackConfigResource.getURL());
                logger.info("Configured logger from " + logbackConfigResourceKey);
            } catch (IOException e) {
                logger.error("Application is unable to load log configuration resource", e);
                throw new MissingResourceException("Application is missing log configuration resource",
                        Resource.class.getName(), logbackConfigResourceKey);
            } catch (JoranException je) {
                // StatusPrinter will handle this
            }
            StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        } else {
            // SLF4J is NOT bound to logback
            logger.error("Unable to configure logback. SLF4J is not bound to logback in this application.");
            throw new ClassCastException("Unable to configure logback. Failed casting ILoggerFactory to logback LoggerContext." +
                    " LoggerFactory.getILoggerFactory() returns " + loggerFactory.getClass().getName());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext) {
            logger.info("Destroying logback logger context");
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.stop();
        }
    }
}
