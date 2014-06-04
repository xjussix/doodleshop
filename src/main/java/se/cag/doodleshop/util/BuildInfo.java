/*
 * Created by Daniel Marell 13-05-04 2:39 PM
 */
package se.cag.doodleshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Provides information about the build.
 * <p/>
 * Application-Version=${pom.version}
 * Revision=${GIT_COMMIT}
 * Build-Tag=${BUILD_TAG}
 * Build-Number=${BUILD_NUMBER}
 */
public class BuildInfo {
    private BuildInfo() {
    }

    /**
     * Get application version including build number.
     *
     * @return The application version
     */
    public static String getApplicationVersion() {
        return getProperty("Application-Version", "Pirate-" + getTimestamp());
    }

    /**
     * Get CI build tag.
     *
     * @return A tag identifying this build
     */
    public static String getBuildTag() {
        return getProperty("Build-Tag", "?");
    }

    /**
     * Get version control revision.
     *
     * @return The version control revision
     */
    public static String getRevision() {
        return getProperty("Revision", "?");
    }

    /**
     * Get build number.
     *
     * @return Build number
     */
    public static String getBuildNumber() {
        return getProperty("Build-Number", "?");
    }

    private static String getProperty(String name, String unsubstituted) {
        InputStream in = BuildInfo.class.getResourceAsStream("/BuildInfo.properties");
        if (in == null) {
            return "Missing BuildInfo.properties";
        }
        try {
            Properties props = new Properties();
            props.load(in);
            String value = props.getProperty(name);
            if (!isSubstituted(value)) {
                return unsubstituted;
            }
            return value;
        } catch (IOException e) {
            return "Cannot read BuildInfo.properties";
        } finally {
            try {
                in.close();
            } catch (IOException ignore) {
            }
        }
    }

    private static boolean isSubstituted(String s) {
        return !s.matches(".*\\$\\{.*\\}.*");
    }

    private static String getTimestamp() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        return df.format(new Date());
    }
}
