package org.wso2.carbon.identity.configuration.mgt.core.model;

/**
 * A model class representing a successful configuration add response.
 */
public class ConfigurationChangeResponse {

    private String configurationName;
    private String tenantDomain;
    private String state;

    public ConfigurationChangeResponse(String configurationName, String tenantDomain, String state) {

        this.tenantDomain = tenantDomain;
        this.configurationName = configurationName;
        this.state = state;
    }

    public String getConfigurationName() {

        return configurationName;
    }

    public void setConfigurationName(String configurationName) {

        this.configurationName = configurationName;
    }

    public String getTenantDomain() {

        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {

        this.tenantDomain = tenantDomain;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }
}
