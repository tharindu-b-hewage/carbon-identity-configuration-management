package org.wso2.carbon.identity.configuration.mgt.core.constant;

/**
 * Constants related to SQL operations.
 */
public class SQLConstants {

    public static final int MAX_QUERY_LENGTH_SQL = Integer.MAX_VALUE; // TODO: 12/11/18 Settle this!
    public static final String INSERT_RESOURCE_TYPE_SQL = "INSERT INTO IDN_CONFIG_TYPE (ID, NAME, DESCRIPTION) " +
            "VALUES (?, ?, ?)";
    public static final String INSERT_RESOURCE_SQL = "INSERT INTO\n" +
            "  IDN_CONFIG_RESOURCE(\n" +
            "    ID,\n" +
            "    TENANT_ID,\n" +
            "    NAME,\n" +
            "    LAST_MODIFIED,\n" +
            "    HAS_FILE,\n" +
            "    HAS_ATTRIBUTE,\n" +
            "    TYPE_ID\n" +
            "  )\n" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_ATTRIBUTES_SQL = "INSERT INTO\n" +
            "  IDN_CONFIG_ATTRIBUTE(\n" +
            "    ID,\n" +
            "    RESOURCE_ID,\n" +
            "    ATTR_KEY,\n" +
            "    ATTR_VALUE\n" +
            "  )\n" +
            "VALUES(?, ?, ?, ?)";
    public static final String INSERT_ATTRIBUTE_KEY_VALUE_SQL = ", (?, ?, ?, ?)";

    // Name is not update since for being the only possible duplicate key
    public static final String REPLACE_RESOURCE_TYPE_SQL = "INSERT INTO IDN_CONFIG_TYPE (ID, NAME, DESCRIPTION) " +
            "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE ID = VALUES(ID), DESCRIPTION = VALUES(DESCRIPTION)"; // TODO: 12/10/18 PUT -> Create new resource type with same name but different uuid, description
    public static final String GET_RESOURCE_TYPE_BY_NAME_SQL = "SELECT ID, NAME, DESCRIPTION FROM IDN_CONFIG_TYPE WHERE NAME = ? ";
    public static final String GET_RESOURCE_TYPE_BY_ID_SQL = "SELECT ID, NAME, DESCRIPTION FROM IDN_CONFIG_TYPE WHERE ID = ? ";
    public static final String DELETE_RESOURCE_TYPE_BY_NAME_SQL = "DELETE FROM IDN_CONFIG_TYPE WHERE NAME = ?";
    public static final String DELETE_RESOURCE_TYPE_BY_ID_SQL = "DELETE FROM IDN_CONFIG_TYPE WHERE ID = ?";

    public static final String GET_RESOURCE_MYSQL = "SELECT\n" +
            "  R.ID,\n" +
            "  R.TENANT_ID,\n" +
            "  R.NAME,\n" +
            "  R.LAST_MODIFIED,\n" +
            "  T.NAME AS RESOURCE_TYPE,\n" +
            "  T.DESCRIPTION AS DESCRIPTION,\n" +
            "  F.ID AS FILE_ID,\n" +
            "  A.ATTR_KEY AS ATTR_KEY,\n" +
            "  A.ATTR_VALUE AS ATTR_VALUE\n" +
            "FROM\n" +
            "  IDN_CONFIG_RESOURCE AS R\n" +
            "  INNER JOIN IDN_CONFIG_TYPE AS T ON R.TYPE_ID = T.ID\n" +
            "  LEFT JOIN IDN_CONFIG_ATTRIBUTE AS A ON (\n" +
            "    R.HAS_ATTRIBUTE = TRUE\n" +
            "    AND A.RESOURCE_ID = R.ID\n" +
            "  )\n" +
            "  LEFT JOIN IDN_CONFIG_FILE AS F ON (\n" +
            "    R.HAS_FILE = TRUE\n" +
            "    AND F.RESOURCE_ID = R.ID\n" +
            "  )\n" +
            "WHERE\n" +
            "  R.NAME = ?\n" +
            "  AND R.TENANT_ID = ?\n" +
            "  AND R.TYPE_ID = ?";
    public static final String GET_TENANT_RESOURCES_SELECT_COLLUMNS_MYSQL = "SELECT\n" +
            "  R.ID,\n" +
            "  R.TENANT_ID,\n" +
            "  R.NAME,\n" +
            "  R.LAST_MODIFIED,\n" +
            "  T.NAME AS RESOURCE_TYPE,\n" +
            "  T.DESCRIPTION AS DESCRIPTION,\n" +
            "  F.ID AS FILE_ID,\n" +
            "  A.ATTR_KEY AS ATTR_KEY,\n" +
            "  A.ATTR_VALUE AS ATTR_VALUE\n" +
            "FROM\n" +
            "  IDN_CONFIG_RESOURCE AS R\n" +
            "  INNER JOIN IDN_CONFIG_TYPE AS T ON R.TYPE_ID = T.ID\n" +
            "  LEFT JOIN IDN_CONFIG_ATTRIBUTE AS A ON (\n" +
            "    R.HAS_ATTRIBUTE = TRUE\n" +
            "    AND A.RESOURCE_ID = R.ID\n" +
            "  )\n" +
            "  LEFT JOIN IDN_CONFIG_FILE AS F ON (\n" +
            "    R.HAS_FILE = TRUE\n" +
            "    AND F.RESOURCE_ID = R.ID\n" +
            "  )\n";
    public static final String DELETE_RESOURCE_SQL = "DELETE FROM\n" +
            "  IDN_CONFIG_RESOURCE\n" +
            "WHERE\n" +
            "  NAME = ?\n" +
            "  AND TENANT_ID = ?\n" +
            "  AND TYPE_ID = ?";

}
