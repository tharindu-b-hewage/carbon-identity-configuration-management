package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAOImpl implements ConfigurationDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPriority() {

        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public Configuration getConfiguration(String name) throws ConfigurationManagementException {

        List<Attribute> attributes = new ArrayList<>(1);
        Attribute tempAttribute = new Attribute("from", "abc@gmail.com");
        attributes.add(tempAttribute);
        return new Configuration("mail", attributes);
    }

    /**
     * {@inheritDoc}
     */
    public String deleteConfiguration(String name) throws ConfigurationManagementException {

        return new String("sample Deleted Configuration");
    }

    /**
     * {@inheritDoc}
     */
    public String addConfiguration(String name, Configuration configuration) throws ConfigurationManagementException {

        return new String("sample added Configuration");
    }

    /**
     * {@inheritDoc}
     */
    public String replaceConfiguration(String name, Configuration configuration) throws ConfigurationManagementException {

        return new String("sample replaced Configuration");
    }

    /**
     * {@inheritDoc}
     */
    public String updateConfiguration(String name, Configuration configuration) throws ConfigurationManagementException {

        return new String("sample updated configuration");
    }
}
