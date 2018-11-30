package org.wso2.carbon.identity.configuration.mgt.core.internal;

import javax.sql.DataSource;

/**
 * A class to keep the data of the configuration manager component.
 */
public class ConfigurationManagerComponentDataHolder {

    private static ConfigurationManagerComponentDataHolder instance = new ConfigurationManagerComponentDataHolder();
    private DataSource dataSource;

    public static ConfigurationManagerComponentDataHolder getInstance() {

        return instance;
    }

    public DataSource getDataSource() {

        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }
}
