package org.wso2.carbon.identity.configuration.mgt.core.search;

import org.wso2.carbon.identity.configuration.mgt.core.search.constant.ConditionType;

import java.util.List;

/**
 * This class represent a complex condition with a {@link ConditionType}. A complex condition can contain a list of
 * another complex conditions or a primitive condition. Ex: A sample complex condition with two complex conditions
 * as a list can represent the form, {@link ComplexCondition}[1] {@link ConditionType}[2] {@link ComplexCondition}[2].
 */
public class ComplexCondition extends Condition{

    private List<ComplexCondition> complexConditions;

    private PrimitiveCondition primitiveCondition;

    public ComplexCondition(ConditionType operation, List<ComplexCondition> complexConditions) {

        super(operation);
        this.complexConditions = complexConditions;
    }

    public ComplexCondition(ConditionType operation, PrimitiveCondition primitiveCondition) {

        super(operation);
        this.primitiveCondition = primitiveCondition;
    }

    public List<ComplexCondition> getComplexConditions() {

        return complexConditions;
    }

    public PrimitiveCondition getPrimitiveCondition() {

        return primitiveCondition;
    }
}
