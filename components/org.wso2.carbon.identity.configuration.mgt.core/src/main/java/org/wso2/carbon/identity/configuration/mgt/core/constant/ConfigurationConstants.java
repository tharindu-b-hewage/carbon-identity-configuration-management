package org.wso2.carbon.identity.configuration.mgt.core.constant;

/**
 * Constants related to configuration management.
 */
public class ConfigurationConstants {

    public static final String STATUS_BAD_REQUEST_MESSAGE_DEFAULT = "Bad Request";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DEFAULT_RESPONSE_CONTENT_TYPE = APPLICATION_JSON;
    public static final String STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT = "Internal server error";
    public static final String STATE_ADD_CONFIGURATION_CHANGE_RESPONSE = "add";
    public static final String STATE_UPDATE_CONFIGURATION_CHANGE_RESPONSE = "update";
    public static final String STATE_REPLACE_CONFIGURATION_CHANGE_RESPONSE = "replace";
    public static final String BASE_PATH = "/api/identity/config-mgt/v1.0/resource/";
    public static final String MY_SQL = "MySQL";
    public static final String POSTGRE_SQL = "PostgreSQL";
    public static final String DB2 = "DB2";
    public static final String MICROSOFT = "Microsoft";
    public static final String S_MICROSOFT = "microsoft";
    public static final String INFORMIX = "Informix";
    public static final String H2 = "H2";

    public enum ErrorMessages {
        ERROR_CODE_SELECT_CONFIGURATION_BY_ID("CONFIGM_00001", "Error occurred while retrieving configuration " +
                "from DB for the name: %s."),
        ERROR_CODE_CONFIGURATION_ALREADY_EXIST("CONFIGM_00002", "Resource with the name: %s already exists."),
        ERROR_CODE_NO_USER_FOUND("CONFIGM_00003", "No authenticated user found to perform the operation: %s."),
        ERROR_CODE_UNEXPECTED("CONFIGM_00004", "Unexpected Error"),
        ERROR_CODE_CONFIGURATION_NAME_REQUIRED("CONFIGM_00005", "Resource name is required."),
        ERROR_CODE_CONFIGURATION_NAME_INVALID("CONFIGM_00006", "Invalid Resource Name: %s"),
        ERROR_CODE_GET_DAO("CONFIGM_00007", "No %s are registered."),
        ERROR_CODE_RESOURCE_TYPE_NAME_MISSING("CONFIGM_00008", "Missing mandatory resource type name."),
        ERROR_CODE_DATABASE_CONNECTION("CONFIGM_00009", "Error when creating the database connection."),
        ERROR_CODE_ADD_RESOURCE_TYPE("CONFIGM_00010", "Error while adding the resource type: %s."),
        ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS("CONFIGM_00011", "Resource type with the name: %s already exists."),
        ERROR_CODE_INVALID_RESOURCE_TYPE_IDENTIFIER("CONFIGM_00012", "Invalid resource type identifiers: %s. " +
                "Either name or id should exists."),
        ERROR_CODE_RETRIEVE_RESOURCE_TYPE("CONFIGM_00013", "Error while getting the resource type: %s."),
        ERROR_CODE_RESOURCE_TYPE_NAME_INVALID("CONFIGM_00014", "Invalid resource type name: %s."),
        ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS("CONFIGM_00015", "Resource type with the name: %s does not exists."),
        ERROR_CODE_DELETE_RESOURCE_TYPE("CONFIGM_00016", "Error while deleting the resource type: %s."),
        ERROR_CODE_REPLACE_RESOURCE_TYPE("CONFIGM_00010", "Error while replacing the resource type: %s."),
        ERROR_CODE_RESOURCE_ADD_REQUEST_INVALID("CONFIGM_00011", "Resource add request validation failed. " +
                "Invalid resource add request."),
        ERROR_CODE_RESOURCE_GET_REQUEST_INVALID("CONFIGM_00012", "Resource get request validation failed"),
        ;

        private final String code;
        private final String message;

        ErrorMessages(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String getCode() {

            return code;
        }

        public String getMessage() {

            return message;
        }

        @Override
        public String toString() {

            return code + ":" + message;
        }
    }

    public enum SearchConditionType {
        AND,
        EQUALS,
        GREATER_OR_EQUALS,
        GREATER_THAN,
        LESS_OR_EQUAL,
        LESS_THAN,
        NOT_EQUALS,
        OR
    }
}
