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

    public enum ErrorMessages {
        ERROR_CODE_SELECT_CONFIGURATION_BY_ID("CONFIGM_00001", "Error occurred while retrieving configuration " +
                "from DB for the name: %s."),
        ERROR_CODE_CONFIGURATION_ALREADY_EXIST("CONFIGM_00002", "Configuration with the name: %s already exists."),
        ERROR_CODE_NO_USER_FOUND("CONFIGM_00003", "No authenticated user found to perform the operation: %s."),
        ERROR_CODE_UNEXPECTED("CONFIGM_00004", "Unexpected Error"),
        ERROR_CODE_CONFIGURATION_NAME_REQUIRED("CONFIGM_00005", "Configuration name is required."),
        ERROR_CODE_CONFIGURATION_NAME_INVALID("CONFIGM_00006", "Invalid Configuration Name: %s"),;

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
}
