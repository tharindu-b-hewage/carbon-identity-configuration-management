package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;

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
    public Resource getConfiguration(java.lang.String name) throws ConfigurationManagementException {

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
        return new Resource("test", "test");
    }

    /**
     * {@inheritDoc}
     */
    public java.lang.String deleteConfiguration(java.lang.String name) throws ConfigurationManagementException {

        return new java.lang.String("sample Deleted Resource");
    }

    /**
     * {@inheritDoc}
     */
    public java.lang.String addConfiguration(java.lang.String name, Resource resource) throws ConfigurationManagementException {

        return new java.lang.String("sample added Resource");
    }

    /**
     * {@inheritDoc}
     */
    public java.lang.String replaceConfiguration(java.lang.String name, Resource resource) throws ConfigurationManagementException {

        return new java.lang.String("sample replaced Resource");
    }

    /**
     * {@inheritDoc}
     */
    public java.lang.String updateConfiguration(java.lang.String name, Resource resource) throws ConfigurationManagementException {

        return new java.lang.String("sample updated resource");
    }
}
