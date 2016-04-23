package com.kumuluz.ee.common.config;

import com.kumuluz.ee.common.exceptions.ServerStartException;
import com.kumuluz.ee.common.utils.EnvUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.logging.Logger;

/**
 * @author Tilen Faganel
 * @since 1.0.0
 */
@XmlRootElement
public class ServerConfig {


    public static final String PORT_ENV = "PORT";

    public static final String MIN_THREADS_ENV = "MIN_THREADS";

    public static final String MAX_THREADS_ENV = "MAX_THREADS";

    public static final String CONTEXT_PATH_ENV = "CONTEXT_PATH";

    public static final String WEBAPP_PATH_ENV = "WEBAPP_PATH";


    private Integer port = 8080;

    private String contextPath = "/";

    private Integer idleTimeout = 60 * 60 * 1000;

    private Integer soLingerTime = -1;

    private Integer minThreads = 5;

    private Integer maxThreads = 100;

    private String webappPath = "webapp";

    private ServerConfig() {

        EnvUtils.getEnvAsInteger(PORT_ENV, this::setPort);
        EnvUtils.getEnvAsInteger(MIN_THREADS_ENV, this::setMinThreads);
        EnvUtils.getEnvAsInteger(MAX_THREADS_ENV, this::setMaxThreads);
        EnvUtils.getEnv(CONTEXT_PATH_ENV, this::setContextPath);
        EnvUtils.getEnv(WEBAPP_PATH_ENV, this::setWebappPath);
    }

    /**
     * Configurations can be inherit from Parent
     *
     * @param parentConfig
     */
    private ServerConfig(ServerConfig parentConfig) {
        this.port = parentConfig.port;
        this.minThreads = parentConfig.minThreads;
        this.maxThreads = parentConfig.maxThreads;
        this.contextPath = parentConfig.contextPath;
        this.webappPath = parentConfig.webappPath;
    }

    public Integer getPort() {
        return port;
    }

    @XmlElement
    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    @XmlElement
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Integer getIdleTimeout() {
        return idleTimeout;
    }

    @XmlElement
    public void setIdleTimeout(Integer idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public Integer getSoLingerTime() {
        return soLingerTime;
    }

    @XmlElement
    public void setSoLingerTime(Integer soLingerTime) {
        this.soLingerTime = soLingerTime;
    }

    public Integer getMinThreads() {
        return minThreads;
    }

    @XmlElement
    public void setMinThreads(Integer minThreads) {
        this.minThreads = minThreads;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    @XmlElement
    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    public String getWebappPath() {
        return webappPath;
    }

    @XmlElement
    public void setWebappPath(String webappPath) {
        this.webappPath = webappPath;
    }


    /**
     * Builder Class for build Server Configuration
     */
    public static final class Builder {
        private static final Logger LOGGER = Logger.getLogger(ServerConfig.Builder.class.getSimpleName());


        /**
         * Get Server Configuration from XML file
         *
         * @return
         * @throws JAXBException
         */
        public ServerConfig getXMLServerConfig(String xmlFilePath) throws ServerStartException {
            JAXBContext jaxbContext = null;
            ServerConfig parent = null;

            LOGGER.info(xmlFilePath);

            try {
                jaxbContext = JAXBContext.newInstance(ServerConfig.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                parent = (ServerConfig) jaxbUnmarshaller.unmarshal(new File(xmlFilePath));
            } catch (JAXBException e) {
                throw new ServerStartException(e);
            } catch (Exception e) {
                throw new ServerStartException(e);
            }
            return new ServerConfig(parent);
        }


        /**
         * GET Server Configuration from ENV variables
         *
         * @return
         */
        public ServerConfig getDefault() {
            return new ServerConfig();
        }


    }


}
