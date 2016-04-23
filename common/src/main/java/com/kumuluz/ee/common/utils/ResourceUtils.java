package com.kumuluz.ee.common.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * @author Tilen Faganel
 * @since 1.0.0
 */
public class ResourceUtils {
    private static final Logger LOGGER = Logger.getLogger(ResourceUtils.class.getSimpleName());


    public static String getProjectWebResources(String resourcePath) throws MalformedURLException {

        resourcePath = resourcePath == null ? "webapp" : resourcePath;
        LOGGER.info(ResourceUtils.class.getClassLoader().getResource("./").getPath()+"");
        LOGGER.info(ResourceUtils.class.getResource("/home")+"");
        URL webapp = new File(resourcePath).toURL();

        if (webapp != null) {

            return webapp.toString();
        }

        return null;
    }

    public static boolean isRunningInJar(String resourcePath) throws MalformedURLException {
        resourcePath = resourcePath == null ? "webapp" : resourcePath;
        URL jar = new File(resourcePath).toURL();

        if (jar == null)
            throw new IllegalStateException("Base resource folder does not exists. Please check " +
                    "your project configuration and make sure you are using maven");

        return jar.toString().toLowerCase().startsWith("jar:");
    }
}
