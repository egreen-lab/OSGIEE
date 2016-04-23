package com.kumuluz.ee.common.config;

import com.kumuluz.ee.common.exceptions.ServerStartException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tilen Faganel
 * @since 1.0.0
 */
public class EeConfig {



    private ServerConfig serverConfig;

    private List<PersistenceConfig> persistenceConfigs = new ArrayList<>();

    public EeConfig(RunTimeParameters runTimeParameters) throws ServerStartException {
        serverConfig=new ServerConfig.Builder().getXMLServerConfig(runTimeParameters.getServerConfigPath());
        persistenceConfigs.add(new PersistenceConfig());
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public List<PersistenceConfig> getPersistenceConfigs() {
        return persistenceConfigs;
    }
}
