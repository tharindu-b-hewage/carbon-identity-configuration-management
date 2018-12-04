package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        Connection connection = IdentityDatabaseUtil.getDBConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM IDN_CONFIG_RESOURCE");
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {

        }
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
