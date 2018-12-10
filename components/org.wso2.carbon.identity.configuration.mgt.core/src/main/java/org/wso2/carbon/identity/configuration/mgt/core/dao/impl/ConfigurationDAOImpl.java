package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.database.utils.jdbc.JdbcTemplate;
import org.wso2.carbon.database.utils.jdbc.exceptions.DataAccessException;
import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages;
import org.wso2.carbon.identity.configuration.mgt.core.constant.SQLConstants;
import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;
import org.wso2.carbon.identity.configuration.mgt.core.util.JdbcUtils;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_ADD_RESOURCE_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_DELETE_RESOURCE_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_REPLACE_RESOURCE_TYPE;
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

        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
        try {
            jdbcTemplate.executeInsert(SQLConstants.INSERT_RESOURCE_TYPE_SQL, preparedStatement -> {
                preparedStatement.setString(1, resourceType.getId());
                preparedStatement.setString(2, resourceType.getName());
                preparedStatement.setString(3, resourceType.getDescription());
            }, resourceType, false);
        } catch (DataAccessException e) {
            throw ConfigurationUtils.handleServerException(ERROR_CODE_ADD_RESOURCE_TYPE, resourceType.getName(), e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param resourceType
     * @throws ConfigurationManagementException
     */
    public void replaceResourceType(ResourceType resourceType) throws ConfigurationManagementException {

//        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
//        try {
//            jdbcTemplate.executeInsert(SQLConstants.REPLACE_RESOURCE_TYPE_SQL, preparedStatement -> {
//
//            })
//        } catch (DataAccessException e) {
//
//        }
    }

    private void replaceResourceType(ResourceType resourceType, Connection connection) throws ConfigurationManagementException {

        PreparedStatement replaceResourceTypePreparedStatement = null;
        try {
            replaceResourceTypePreparedStatement = connection.prepareStatement(
                    SQLConstants.REPLACE_RESOURCE_TYPE_SQL);
            replaceResourceTypePreparedStatement.setString(1, resourceType.getId());
            replaceResourceTypePreparedStatement.setString(2, resourceType.getName());
            replaceResourceTypePreparedStatement.setString(3, resourceType.getDescription());

            // Name is not update since it's the only possible duplicate key here. However a new UUID is generated
            // since PUT replaces the existing value.
            replaceResourceTypePreparedStatement.setString(4, resourceType.getId());
            replaceResourceTypePreparedStatement.setString(5, resourceType.getDescription());
            replaceResourceTypePreparedStatement.execute();
        } catch (SQLException e) {
            IdentityDatabaseUtil.rollBack(connection);
            throw ConfigurationUtils.handleServerException(ERROR_CODE_REPLACE_RESOURCE_TYPE, resourceType.getName(), e);
        } finally {
            IdentityDatabaseUtil.closeStatement(replaceResourceTypePreparedStatement);
        }
    }

    @Override
    public ResourceType getResourceTypeByName(String name) throws ConfigurationManagementException {

        return getResourceTypeByIdentifier(name, null);
    }

    @Override
    public ResourceType getResourceTypeById(String id) throws ConfigurationManagementException {

        return getResourceTypeByIdentifier(null, id);
    }

    private ResourceType getResourceTypeByIdentifier(String name, String id) throws ConfigurationManagementException {

        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
        ResourceType resourceTypeResponse;
        try {
            resourceTypeResponse = jdbcTemplate.fetchSingleRecord(
                    selectGetResourceTypeQuery(id),
                    (resultSet, rowNumber) -> {
                        ResourceType resourceType = new ResourceType();
                        resourceType.setId(resultSet.getString("ID"));
                        resourceType.setName(resultSet.getString("NAME"));
                        resourceType.setDescription(resultSet.getString("DESCRIPTION"));
                        return resourceType;
                    }, preparedStatement ->
                            preparedStatement.setString(1, StringUtils.isEmpty(name) ? id : name)
            );
            return resourceTypeResponse;
        } catch (DataAccessException e) {
            throw ConfigurationUtils.handleServerException(ERROR_CODE_RETRIEVE_RESOURCE_TYPE, name, e);
        }
    }

    private String selectGetResourceTypeQuery(String id) {

        return StringUtils.isEmpty(id) ? SQLConstants.GET_RESOURCE_TYPE_BY_NAME_SQL :
                SQLConstants.GET_RESOURCE_TYPE_BY_ID_SQL;
    }

    public void deleteResourceTypeByName(String name) throws ConfigurationManagementException {

        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
        try {
            jdbcTemplate.executeUpdate(selectDeleteResourceTypeQuery(null), (preparedStatement -> {
                preparedStatement.setString(1, name);
            }
            ));
        } catch (DataAccessException e) {
            throw ConfigurationUtils.handleServerException(ERROR_CODE_DELETE_RESOURCE_TYPE, name, e);
        }
    }

    private String selectDeleteResourceTypeQuery(String id) {

        return StringUtils.isEmpty(id) ? SQLConstants.DELETE_RESOURCE_TYPE_BY_NAME_SQL :
                SQLConstants.DELETE_RESOURCE_TYPE_BY_ID_SQL;
    }

    protected Connection getConnection() {

        return IdentityDatabaseUtil.getDBConnection();
    }
}
