package org.wso2.carbon.identity.configuration.mgt.core.constant;

/**
 * Constants related to SQL operations.
 */
public class SQLConstants {

    public static final String INSERT_RESOURCE_TYPE_SQL = "INSERT INTO IDN_CONFIG_TYPE (ID, NAME, DESCRIPTION) " +
            "VALUES (?, ?, ?)";
    public static final String GET_RESOURCE_TYPE_BY_NAME_SQL = "SELECT ID, NAME, DESCRIPTION FROM IDN_CONFIG_TYPE WHERE NAME = ? ";
    public static final String GET_RESOURCE_TYPE_BY_ID_SQL = "SELECT ID, NAME, DESCRIPTION FROM IDN_CONFIG_TYPE WHERE ID = ? ";
}
