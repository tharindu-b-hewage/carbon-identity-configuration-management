package org.wso2.carbon.identity.configuration.mgt.core.search;

import org.wso2.carbon.identity.configuration.mgt.core.search.constant.ConditionType;

/**
 * Represents a primitive search expression. Ex: 'a > 5' where property is 'a', operator is '>' and value is '5'.
 */
public class PrimitiveCondition extends Condition {

    private String property;
    private Object value;

    public PrimitiveCondition(String property, ConditionType operation, Object value) {

        super(operation);
        this.property = property;
        this.value = value;
    }

    public String getProperty() {

        return property;
    }

    public void setProperty(String property) {

        this.property = property;
    }

    public Object getValue() {

        return value;
    }

    public void setValue(Object value) {

        this.value = value;
    }
}
