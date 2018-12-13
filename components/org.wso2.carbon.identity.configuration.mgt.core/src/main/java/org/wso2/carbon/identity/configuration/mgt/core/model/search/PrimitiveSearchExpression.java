package org.wso2.carbon.identity.configuration.mgt.core.model.search;

/**
 * Represents a primitive search expression. Ex: 'a > 5' where property is 'a', operator is '>' and value is '5'.
 */
public class PrimitiveSearchExpression {

    private String property;
    private String operator;
    private String value;

    public PrimitiveSearchExpression(String property, String operator, String value) {

        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public PrimitiveSearchExpression() {

    }

    public String getProperty() {

        return property;
    }

    public void setProperty(String property) {

        this.property = property;
    }

    public String getOperator() {

        return operator;
    }

    public void setOperator(String operator) {

        this.operator = operator;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }
}
