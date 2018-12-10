package org.wso2.carbon.identity.configuration.mgt.core.constant;

/**
 * Constants related to SQL operations.
 */
public class SQLConstants {

    public static final String INSERT_RESOURCE_TYPE_SQL = "INSERT INTO IDN_CONFIG_TYPE (ID, NAME, DESCRIPTION) " +
            "VALUES (?, ?, ?)";

    // Name is not update since for being the only possible duplicate key
    public static final String REPLACE_RESOURCE_TYPE_SQL = "INSERT INTO IDN_CONFIG_TYPE (ID, NAME, DESCRIPTION) " +
            "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE ID = VALUES(ID), DESCRIPTION = VALUES(DESCRIPTION)"; // TODO: 12/10/18 PUT -> Create new resource type with same name but different uuid, description
    public static final String GET_RESOURCE_TYPE_BY_NAME_SQL = "SELECT ID, NAME, DESCRIPTION FROM IDN_CONFIG_TYPE WHERE NAME = ? ";
    public static final String GET_RESOURCE_TYPE_BY_ID_SQL = "SELECT ID, NAME, DESCRIPTION FROM IDN_CONFIG_TYPE WHERE ID = ? ";
    public static final String DELETE_RESOURCE_TYPE_BY_NAME_SQL = "DELETE FROM IDN_CONFIG_TYPE WHERE NAME = ?";
    public static final String DELETE_RESOURCE_TYPE_BY_ID_SQL = "DELETE FROM IDN_CONFIG_TYPE WHERE ID = ?";

    public static final String GET_RESOURCE_BY_NAME_AND_TYPE = "";
}
