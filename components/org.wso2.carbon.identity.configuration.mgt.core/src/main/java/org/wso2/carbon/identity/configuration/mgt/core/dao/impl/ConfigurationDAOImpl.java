package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
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
    public Resource getConfiguration(String name) throws ConfigurationManagementException {

//        Connection connection = IdentityDatabaseUtil.getDBConnection();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM IDN_CONFIG_RESOURCE");
//            ResultSet resultSet = statement.executeQuery();
//        } catch (SQLException e) {
//
//        }
//        List<Attribute> attributes = new ArrayList<>(1);
//        Attribute tempAttribute = new Attribute("from", "abc@gmail.com");
//        attributes.add(tempAttribute);
//        return new Resource("mail", attributes);
        return new Resource("test", new ResourceType("testType"));
    }

    /**
     * {@inheritDoc}
     */
    public String deleteConfiguration(String name) throws ConfigurationManagementException {

        return new String("sample Deleted Resource");
    }

    /**
     * {@inheritDoc}
     */
    public String addConfiguration(String name, Resource resource) throws ConfigurationManagementException {

        return new String("sample added Resource");
    }

    /**
     * {@inheritDoc}
     */
    public String replaceConfiguration(String name, Resource resource) throws ConfigurationManagementException {

        return new String("sample replaced Resource");
    }

    /**
     * {@inheritDoc}
     */
    public String updateConfiguration(String name, Resource resource) throws ConfigurationManagementException {

        return new String("sample updated resource");
    }
}
