package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.database.utils.jdbc.JdbcTemplate;
import org.wso2.carbon.database.utils.jdbc.exceptions.DataAccessException;
import org.wso2.carbon.database.utils.jdbc.exceptions.TransactionException;
import org.wso2.carbon.identity.configuration.mgt.core.constant.SQLConstants;
import org.wso2.carbon.identity.configuration.mgt.core.dao.ConfigurationDAO;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceFile;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceType;
import org.wso2.carbon.identity.configuration.mgt.core.util.ConfigurationUtils;
import org.wso2.carbon.identity.configuration.mgt.core.util.JdbcUtils;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_ADD_RESOURCE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_ADD_RESOURCE_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_DELETE_RESOURCE_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_GET_RESOURCE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_QUERY_LENGTH_EXCEEDED;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants.ErrorMessages.ERROR_CODE_RETRIEVE_RESOURCE_TYPE;
import static org.wso2.carbon.identity.configuration.mgt.core.constant.SQLConstants.MAX_QUERY_LENGTH_SQL;

public class ConfigurationDAOImpl implements ConfigurationDAO {

    private static final Log log = LogFactory.getLog(ConfigurationDAOImpl.class);

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
    public Resource getResource(String name, String resourceTypeId, int tenantId)
            throws ConfigurationManagementException {

        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
        List<ConfigurationRawDataCollector> configurationRawDataCollectors;
        try {
            configurationRawDataCollectors = jdbcTemplate.executeQuery(SQLConstants.GET_RESOURCE_MYSQL,
                    (resultSet, rowNumber) -> new ConfigurationRawDataCollector.ConfigurationRawDataCollectorBuilder()
                            .setResourceId(resultSet.getString("ID"))
                            .setTenantId(resultSet.getInt("TENANT_ID"))
                            .setResourceName(resultSet.getString("NAME"))
                            .setLastModified(resultSet.getString("LAST_MODIFIED"))
                            .setResourceName(resultSet.getString("NAME"))
                            .setResourceTypeName(resultSet.getString("RESOURCE_TYPE"))
                            .setResourceTypeDescription(resultSet.getString("DESCRIPTION"))
                            .setAttributeKey(resultSet.getString("ATTR_KEY"))
                            .setAttributeValue(resultSet.getString("ATTR_VALUE"))
                            .setFileId(resultSet.getString("FILE_ID"))
                            .build(), preparedStatement -> {
                        preparedStatement.setString(1, name);
                        preparedStatement.setInt(2, tenantId);
                        preparedStatement.setString(3, resourceTypeId);
                    });
            /*
            Database call can contain duplicate data for some columns. Need to filter them in order to build the
            resource.
            */
            return configurationRawDataCollectors == null || configurationRawDataCollectors.size() == 0 ?
                    null : buildResourceFromRawData(configurationRawDataCollectors);
        } catch (DataAccessException e) {
            throw ConfigurationUtils.handleServerException(ERROR_CODE_GET_RESOURCE, name, e);
        }
    }

    private Resource buildResourceFromRawData(List<ConfigurationRawDataCollector> configurationRawDataCollectors) {

        Resource resource = new Resource();
        List<Attribute> attributes = new ArrayList<>(0);
        List<ResourceFile> resourceFiles = new ArrayList<>(0);
        Set<String> attributeKeySet = new HashSet<>();
        Set<String> fileIdSet = new HashSet<>();
        configurationRawDataCollectors.forEach(configurationRawDataCollector -> {
            if (resource.getResourceId() == null) {
                resource.setResourceId(configurationRawDataCollector.getResourceId());
            }
            if (resource.getResourceName() == null) {
                resource.setResourceName(configurationRawDataCollector.getResourceName());
            }
            if (resource.getResourceType() == null) {
                resource.setResourceType(configurationRawDataCollector.getResourceTypeName());
            }
            if (resource.getLastModified() == null) {
                resource.setLastModified(configurationRawDataCollector.getLastModified());
            }
            if (resource.getTenantDomain() == null) {
                resource.setTenantDomain(IdentityTenantUtil.getTenantDomain(configurationRawDataCollector.getTenantId()));
            }
            if (!attributeKeySet.contains(configurationRawDataCollector.getAttributeKey())) {
                attributeKeySet.add(configurationRawDataCollector.getAttributeKey());
                if (configurationRawDataCollector.getAttributeKey() != null) {
                    attributes.add(new Attribute(
                            configurationRawDataCollector.getAttributeKey(),
                            configurationRawDataCollector.getAttributeValue()
                    ));
                }
            }
            if (!fileIdSet.contains(configurationRawDataCollector.getFileId())) {
                fileIdSet.add(configurationRawDataCollector.getFileId());
                if (configurationRawDataCollector.getFileId() != null) {
                    resourceFiles.add(new ResourceFile(configurationRawDataCollector.getFileId()));
                }
            }
        });
        resource.setAttribute(attributes);
        resource.setFile(resourceFiles);
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteResource(String name, String resourceType) throws ConfigurationManagementException {

        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
        String resourceTypeId = getResourceTypeByName(resourceType).getId();
        int tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
        try {
            jdbcTemplate.executeUpdate(SQLConstants.DELETE_RESOURCE_SQL, preparedStatement -> {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, tenantId);
                preparedStatement.setString(3, resourceTypeId);
            });
        } catch (DataAccessException e) {
            throw ConfigurationUtils.handleServerException(ERROR_CODE_DELETE_RESOURCE_TYPE, name, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addResource(Resource resource) throws ConfigurationManagementException {

        String resourceTypeId = getResourceTypeByName(resource.getResourceType()).getId();

        JdbcTemplate jdbcTemplate = JdbcUtils.getNewTemplate();
        try {
            jdbcTemplate.withTransaction(template -> {
                boolean isAttributeExists = resource.getAttribute() != null;

                // Insert resource metadata.
                template.executeInsert(SQLConstants.INSERT_RESOURCE_SQL, preparedStatement -> {
                    preparedStatement.setString(1, resource.getResourceId());
                    preparedStatement.setInt(2, PrivilegedCarbonContext.getThreadLocalCarbonContext()
                            .getTenantId());
                    preparedStatement.setString(3, resource.getResourceName());
                    preparedStatement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()),
                            Calendar.getInstance(TimeZone.getTimeZone(UTC)));
                    preparedStatement.setBoolean(5, true); // TODO: 12/12/18 Validate with the file existence earlier in the flow
                    preparedStatement.setBoolean(6, isAttributeExists);
                    preparedStatement.setString(7, resourceTypeId);
                }, resource, false);

                // Insert attributes.
                if (isAttributeExists) {
                    // Create sql query for attribute parameters.
                    String attributesQuery = buildQueryForAttributes(resource);
                    template.executeInsert(attributesQuery, preparedStatement -> {
                        int attributeCount = 0;
                        for (Attribute attribute : resource.getAttribute()) {
                            preparedStatement.setString(++attributeCount, ConfigurationUtils.generateUniqueID());
                            preparedStatement.setString(++attributeCount, resource.getResourceId());
                            preparedStatement.setString(++attributeCount, attribute.getKey());
                            preparedStatement.setString(++attributeCount, attribute.getValue());
                        }
                    }, resource, false);
                }
                return null;
            });
        } catch (TransactionException e) {
            throw ConfigurationUtils.handleServerException(ERROR_CODE_ADD_RESOURCE, resource.getResourceName(), e);
        }
    }

    private String buildQueryForAttributes(Resource resource) throws ConfigurationManagementClientException {

        StringBuilder sb = new StringBuilder();
        sb.append(SQLConstants.INSERT_ATTRIBUTES_SQL);

        // Since attributes exist, query is already built for the first attribute.
        for (int i = 1; i < resource.getAttribute().size(); i++) {
            sb.append(SQLConstants.INSERT_ATTRIBUTE_KEY_VALUE_SQL);
            if (sb.length() > MAX_QUERY_LENGTH_SQL) {
                if (log.isDebugEnabled()) {
                    log.debug("Error building SQL query for the attribute insert. Number of attributes: " +
                            resource.getAttribute().size() + " exceeds the maximum limit: " +
                            MAX_QUERY_LENGTH_SQL);
                }
                throw ConfigurationUtils.handleClientException(ERROR_CODE_QUERY_LENGTH_EXCEEDED, null);
            }
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public void replaceResource(String name, Resource resource) throws ConfigurationManagementException {

//        return new Resource("test", "test");
    }

    /**
     * {@inheritDoc}
     */
    public void updateConfiguration(String name, Resource resource) throws ConfigurationManagementException {

//        return new Resource("test", "test");
    }

    /**
     * {@inheritDoc}
     *
     * @param resourceType
     * @throws ConfigurationManagementException
     */
    public void addResourceType(ResourceType resourceType) throws ConfigurationManagementException {

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

}
