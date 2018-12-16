package org.wso2.carbon.identity.configuration.mgt.core.constant;

import java.util.Random;

/**
 * Constants related to configuration management.
 */
public class ConfigurationConstants {

    public static final int NON_EXISTING_TENANT_ID = new Random().nextInt();
    public static final String STATUS_BAD_REQUEST_MESSAGE_DEFAULT = "Bad Request";
    public static final String STATUS_NOT_FOUND_MESSAGE_DEFAULT = "Not Found";
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
    public static final String ODATA2_API_URI_EXPRESSION_PARSER_ERROR
            = "org.apache.olingo.odata2.api.uri.expression.ExpressionParserException";
    public static final String ODATA2_API_URI_EXPRESSION_PARSER_TOKENIZE_ERROR
        = "org.apache.olingo.odata2.core.uri.expression.TokenizerException";
    public static final String CXF_SEARCH_PARSER_ERROR = "org.apache.cxf.jaxrs.ext.search.SearchParseException";
    public static final String STATUS_ODATA_EXPRESSION_PARSER_ERROR_MESSAGE
            = "Error occurred while parsing the odata2 search expression. Please check the filter query syntax.";
    public static final String BEAN_FIELD_FLAG = "BEAN_FIELD_FLAG__";

    public enum ErrorMessages {
        ERROR_CODE_SELECT_CONFIGURATION_BY_ID("CONFIGM_00001", "Error occurred while retrieving configuration " +
                "from DB for the name: %s."),
        ERROR_CODE_CONFIGURATION_ALREADY_EXIST("CONFIGM_00002", "Resource with the name: %s already exists."),
        ERROR_CODE_NO_USER_FOUND("CONFIGM_00003", "No authenticated user found to perform the operation: %s."),
        ERROR_CODE_UNEXPECTED("CONFIGM_00004", "Unexpected Error"),
        ERROR_CODE_CONFIGURATION_NAME_REQUIRED("CONFIGM_00005", "Resource name is required."),
        ERROR_CODE_RESOURCE_NAME_INVALID("CONFIGM_00006", "Invalid Resource Name: %s"),
        ERROR_CODE_GET_DAO("CONFIGM_00007", "No %s are registered."),
        ERROR_CODE_RESOURCE_TYPE_NAME_REQUIRED("CONFIGM_00008", "Resource type name: %s validation failed."),
        ERROR_CODE_DATABASE_CONNECTION("CONFIGM_00009", "Error when creating the database connection."),
        ERROR_CODE_ADD_RESOURCE_TYPE("CONFIGM_00010", "Error while adding the resource type: %s."),
        ERROR_CODE_RESOURCE_TYPE_ALREADY_EXISTS("CONFIGM_00011", "Resource type with the name: %s already exists."),
        ERROR_CODE_INVALID_RESOURCE_TYPE_IDENTIFIER("CONFIGM_00012", "Invalid resource type identifiers: %s. " +
                "Either name or id should exists."),
        ERROR_CODE_RETRIEVE_RESOURCE_TYPE("CONFIGM_00013", "Error while getting the resource type: %s."),
        ERROR_CODE_RESOURCE_TYPE_NAME_INVALID("CONFIGM_00014", "Invalid resource type name: %s."),
        ERROR_CODE_RESOURCE_TYPE_DOES_NOT_EXISTS("CONFIGM_00015", "Resource type with the name: %s does not exists."),
        ERROR_CODE_DELETE_RESOURCE_TYPE("CONFIGM_00016", "Error while deleting the resource type: %s."),
        ERROR_CODE_REPLACE_RESOURCE_TYPE("CONFIGM_00017", "Error while replacing the resource type: %s."),
        ERROR_CODE_RESOURCE_ADD_REQUEST_INVALID("CONFIGM_00018", "Resource add request validation failed. " +
                "Invalid resource add request."),
        ERROR_CODE_RESOURCE_GET_REQUEST_INVALID("CONFIGM_00019", "Resource get request validation failed"),
        ERROR_CODE_GET_RESOURCE("CONFIGM_00020", "Error while getting the resource : %s."),
        ERROR_CODE_RESOURCE_ALREADY_EXISTS("CONFIGM_00021", "Resource with the name: %s already exists."),
        ERROR_CODE_ADD_RESOURCE("CONFIGM_00021", "Error while adding the resource : %s."),
        ERROR_CODE_QUERY_LENGTH_EXCEEDED("CONFIGM_00022", "Too large SQL query length."),
        ERROR_CODE_RESOURCE_DELETE_REQUEST_REQUIRED("CONFIGM_00023", "Resource delete request validation failed. " +
                "Invalid resource delete request."),
        ERROR_CODE_RESOURCE_DOES_NOT_EXISTS("CONFIGM_00024", "Resource with the name: %s does not exists."),
        ERROR_CODE_ADD_DELETE_RESOURCE("CONFIGM_00025", "Error while deleting the resource: %s."),
        ERROR_CODE_SEARCH_REQUEST_INVALID("CONFIGM_00026", "Search request validation failed. " +
                "Invalid search filter."),
        ERROR_CODE_SEARCH_SQL_EXPRESSION_INVALID("CONFIGM_00027", "Search query expression: %s is not valid."),
        ERROR_CODE_SEARCH_TENANT_RESOURCES("CONFIGM_00028", "Error occurred while searching for resources."),
        ERROR_CODE_RESOURCES_DOES_NOT_EXISTS("CONFIGM_00029", "Resources does not exists."),
        ERROR_CODE_SEARCH_QUERY_PROPERTY_DOES_NOT_EXISTS("CONFIGM_00030", "Search query property: %s is either invalid or not " +
                "found in the permitted properties."),
        ERROR_CODE_SEARCH_QUERY_SQL_PROPERTY_PARSE_ERROR("CONFIGM_00031", "Search query syntax error in the condition: %s."),
        ERROR_CODE_SEARCH_QUERY_SQL_PARSE_ERROR("CONFIGM_00032", "Search query syntax error"),
        ERROR_CODE_ATTRIBUTE_IDENTIFIERS_REQUIRED("CONFIGM_00033", "One or more identifiers for the attribute: %s " +
                "validation failed."),
        ERROR_CODE_REPLACE_ATTRIBUTE("CONFIGM_00034", "Error while replacing the attribute: %s."),
        ERROR_CODE_GET_ATTRIBUTE("CONFIGM_00035", "Error while getting the attribute: %s."),
        ERROR_CODE_ATTRIBUTE_REQUIRED("CONFIGM_00036", "Attribute validation failed"),
        ERROR_CODE_ATTRIBUTE_DOES_NOT_EXISTS("CONFIGM_00037", "Attribute with the key: %s does not exists."),
        ERROR_CODE_UPDATE_ATTRIBUTE("CONFIGM_00038", "Error while updating the attribute: %s."),
        ERROR_CODE_DELETE_ATTRIBUTE("CONFIGM_00039", "Error while deleting the attribute: %s."),
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

    public enum OdataLogicalOperator{
        eq,
        ne,
        lt,
        le,
        gt,
        ge,
        and,
        or
    }
}
