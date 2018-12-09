package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages;
import org.wso2.carbon.identity.configuration.mgt.core.constant.SQLConstants;
import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_ADD_RESOURCE_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RETRIEVE_RESOURCE_TYPE;

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
    public Resource getResource(String name) throws ConfigurationManagementException {

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
    public void deleteResource(String name) throws ConfigurationManagementException {

    }

    /**
     * {@inheritDoc}
     */
    public Resource addResource(String name, ResourceAdd resourceAdd) throws ConfigurationManagementException {

        return new Resource("test", "test");
    }

    /**
     * {@inheritDoc}
     */
    public Resource replaceResource(String name, Resource resource) throws ConfigurationManagementException {

        return new Resource("test", "test");
    }

    /**
     * {@inheritDoc}
     */
    public Resource updateConfiguration(String name, Resource resource) throws ConfigurationManagementException {

        return new Resource("test", "test");
    }

    /**
     * {@inheritDoc}
     *
     * @param resourceType
     * @throws ConfigurationManagementException
     */
    public void addResourceType(ResourceType resourceType) throws ConfigurationManagementException {

        try (Connection connection = getConnection()) {
            addResourceType(resourceType, connection);
        } catch (SQLException e) {
            throw ConfigurationUtils.handleRuntimeException(
                    ErrorMessages.ERROR_CODE_DATABASE_CONNECTION, null);
        }
    }

    private void addResourceType(ResourceType resourceType, Connection connection) throws ConfigurationManagementException {

        PreparedStatement addResourceTypePreparedStatement = null;
        try {
            addResourceTypePreparedStatement = connection.prepareStatement(
                    SQLConstants.INSERT_RESOURCE_TYPE_SQL);
            addResourceTypePreparedStatement.setString(1, resourceType.getId());
            addResourceTypePreparedStatement.setString(2, resourceType.getName());
            addResourceTypePreparedStatement.setString(3, resourceType.getDescription());
            addResourceTypePreparedStatement.execute();
        } catch (SQLException e) {
            IdentityDatabaseUtil.rollBack(connection);
            throw ConfigurationUtils.handleServerException(ERROR_CODE_ADD_RESOURCE_TYPE, resourceType.getName(), e);
        } finally {
            IdentityDatabaseUtil.closeStatement(addResourceTypePreparedStatement);
        }
    }

    @Override
    public ResourceType getResourceTypeByName(String name) throws ConfigurationManagementException {

        try (Connection connection = getConnection()) {
            return getResourceTypeByIdentifier(name, null, connection);
        } catch (SQLException e) {
            throw ConfigurationUtils.handleRuntimeException(
                    ErrorMessages.ERROR_CODE_DATABASE_CONNECTION, null);
        }
    }

    @Override
    public ResourceType getResourceTypeById(String id) throws ConfigurationManagementException {

        try (Connection connection = getConnection()) {
            return getResourceTypeByIdentifier(null, id, connection);
        } catch (SQLException e) {
            throw ConfigurationUtils.handleRuntimeException(
                    ErrorMessages.ERROR_CODE_DATABASE_CONNECTION, null);
        }
    }

    private ResourceType getResourceTypeByIdentifier(String name, String id, Connection connection) throws ConfigurationManagementException {

        PreparedStatement getResourceTypePreparedStatement = null;
        try {
            getResourceTypePreparedStatement = connection.prepareStatement(
                    selectGetResourceTypeQuery(name, id)
            );
            getResourceTypePreparedStatement.setString(
                    1,
                    StringUtils.isEmpty(name) ? id : name
            );
            ResultSet resultSet = getResourceTypePreparedStatement.executeQuery();

            ResourceType resourceType = null;
            if (resultSet.next()) {
                resourceType = new ResourceType();
                resourceType.setId(resultSet.getString("ID"));
                resourceType.setName(resultSet.getString("NAME"));
                resourceType.setDescription(resultSet.getString("DESCRIPTION"));
            }
            return resourceType;
        } catch (SQLException e) {
            IdentityDatabaseUtil.rollBack(connection);
            throw ConfigurationUtils.handleServerException(ERROR_CODE_RETRIEVE_RESOURCE_TYPE, name, e);
        } finally {
            IdentityDatabaseUtil.closeStatement(getResourceTypePreparedStatement);
        }
    }

    private String selectGetResourceTypeQuery(String name, String id) {

        return StringUtils.isEmpty(id) ? SQLConstants.GET_RESOURCE_TYPE_BY_NAME_SQL :
                SQLConstants.GET_RESOURCE_TYPE_BY_ID_SQL;
    }

    protected Connection getConnection() {

        return IdentityDatabaseUtil.getDBConnection();
    }
}
