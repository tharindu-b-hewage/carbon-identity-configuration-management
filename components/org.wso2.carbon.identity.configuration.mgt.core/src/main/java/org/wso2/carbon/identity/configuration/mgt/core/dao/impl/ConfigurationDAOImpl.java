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
}
