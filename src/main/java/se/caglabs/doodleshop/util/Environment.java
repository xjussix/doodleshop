/*
 * Created by Daniel Marell 14-05-07 19:03
 */
package se.caglabs.doodleshop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;

public enum Environment {
    LOCAL, IT, TEST, PROD;

    private static Logger logger = LoggerFactory.getLogger(Environment.class);

    private static final String ENVIRONMENT_PROPERTY = "se.caglabs.doodleshop.environment";

    public static Environment getCurrentEnvironment() {
        return getEnvironment(System.getProperty(ENVIRONMENT_PROPERTY));
    }

    public static void setEnvironment(Environment e) {
        System.setProperty(ENVIRONMENT_PROPERTY, e.name());
    }

    private static Environment getEnvironment(String environmentParam) {
        Environment result;
        if (environmentParam != null) {
            try {
                result = Environment.valueOf(environmentParam.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidPropertyException(Environment.class, environmentParam.toUpperCase(),
                        "Unable to detect environment based property " + ENVIRONMENT_PROPERTY + ":" + e.getMessage());
            }
        } else {
            result = LOCAL;
        }
        logger.info("environment=" + result.name());
        return result;
    }
}
