package com.kumuluz.ee.common.config;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by dewmal on 4/23/16.
 */
public class RunTimeParameters {
    private static final Logger LOGGER = Logger.getLogger(RunTimeParameters.class.getSimpleName());

    public static final String SERVER_CONFIG_PATH_PARAMETER_NAME = "-c";


    private String serverConfigPath;

    public String getServerConfigPath() {
        return serverConfigPath;
    }

    public void setServerConfigPath(String serverConfigPath) {
        this.serverConfigPath = serverConfigPath;
    }


    private RunTimeParameters() {
    }

    public static final class RunTimeParameterBuilder {


        public static RunTimeParameters getParameters(String args[]) {
            RunTimeParameters parameters = new RunTimeParameters();


            for (String arg : args) {
                switch (arg.toLowerCase().substring(0, 2)) {
                    case "-c": {
                        parameters.setServerConfigPath(arg.replace("-c", ""));
                    }
                }
            }
            LOGGER.info(parameters.getServerConfigPath());

            return parameters;
        }

    }
}
