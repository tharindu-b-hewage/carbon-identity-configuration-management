package org.wso2.carbon.identity.configuration.mgt.core.search.constant;

public enum ConditionType {

    EQUALS,
    NOT_EQUALS,
    LESS_THAN,
    GREATER_THAN,
    LESS_OR_EQUALS,
    GREATER_OR_EQUALS,
    OR,
    AND;

    // Logical SQL operator will be honoured.
    public String toSQL() {

        String op = null;
        switch (this) {
            case EQUALS:
                op = "=";
                break;
            case NOT_EQUALS:
                op = "<>";
                break;
            case GREATER_THAN:
                op = ">";
                break;
            case GREATER_OR_EQUALS:
                op = ">=";
                break;
            case LESS_THAN:
                op = "<";
                break;
            case LESS_OR_EQUALS:
                op = "<=";
                break;
            case OR:
                op = this.toString();
                break;
            case AND:
                op = this.toString();
                break;
        }
        return op;
    }
}
