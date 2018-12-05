package org.wso2.carbon.identity.configuration.mgt.core.model;

import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;

import java.util.List;

/**
 * This class represents the configuration data holder for the Resource Manager class.
 */
public class ConfigurationManagerConfigurationHolder {

    private List<ConfigurationDAO> configurationDAOS;

    public List<ConfigurationDAO> getConfigurationDAOS() {

        return configurationDAOS;
    }

    public void setConfigurationDAOS(List<ConfigurationDAO> configurationDAOS) {

        this.configurationDAOS = configurationDAOS;
    }
}
