package org.wso2.carbon.identity.configuration.mgt.core.model.search;

import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;

public class PrimitiveStatement {

    private String property;
    private ConfigurationConstants.SearchConditionType searchConditionType;
    private Object value;

    public String getProperty() {

        return property;
    }

    public void setProperty(String property) {

        this.property = property;
    }

    public ConfigurationConstants.SearchConditionType getSearchConditionType() {

        return searchConditionType;
    }

    public void setSearchConditionType(ConfigurationConstants.SearchConditionType searchConditionType) {

        this.searchConditionType = searchConditionType;
    }

    public Object getValue() {

        return value;
    }

    public void setValue(Object value) {

        this.value = value;
    }
}
