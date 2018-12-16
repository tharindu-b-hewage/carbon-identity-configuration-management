package org.wso2.carbon.identity.configuration.mgt.core.search;

import org.wso2.carbon.identity.configuration.mgt.core.search.constant.ConditionType;

/**
 * Represent the base class for {@link PrimitiveCondition} and {@link ComplexCondition}.
 *
 * @param <T> Reference bean class containing allowed search properties as fields.
 */
public class Condition {

    private ConditionType operation;

    public Condition(ConditionType operation) {

        this.operation = operation;
    }

    public ConditionType getOperation() {

        return operation;
    }

    public void setOperation(ConditionType operation) {

        this.operation = operation;
    }
}
