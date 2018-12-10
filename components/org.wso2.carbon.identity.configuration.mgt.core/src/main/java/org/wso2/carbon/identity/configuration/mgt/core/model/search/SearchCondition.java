package org.wso2.carbon.identity.configuration.mgt.core.model.search;

import org.wso2.carbon.identity.configuration.mgt.core.constant.ConfigurationConstants;

import java.util.List;

public class SearchCondition {

    public PrimitiveStatement getPrimitiveStatement() {

        return primitiveStatement;
    }

    public List<SearchCondition> getSearchConditions() {

        return searchConditions;
    }

    public ConfigurationConstants.SearchConditionType getConditionType() {

        return conditionType;
    }

    private PrimitiveStatement primitiveStatement;
    private List<SearchCondition> searchConditions;
    private ConfigurationConstants.SearchConditionType conditionType;

    public SearchCondition(PrimitiveStatement primitiveStatement) {

        this.primitiveStatement = primitiveStatement;
    }

    public SearchCondition(List<SearchCondition> searchConditions,
                           ConfigurationConstants.SearchConditionType conditionType) {

        this.searchConditions = searchConditions;
        this.conditionType = conditionType;
    }

}
