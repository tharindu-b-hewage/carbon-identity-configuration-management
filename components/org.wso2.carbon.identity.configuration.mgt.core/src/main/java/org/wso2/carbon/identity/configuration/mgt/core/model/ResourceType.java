package org.wso2.carbon.identity.configuration.mgt.core.model;

/**
 * A model class representing a resource type.
 */
public class ResourceType {

    private java.lang.String name;

    private java.lang.String id;

    private java.lang.String description;

    public ResourceType(java.lang.String name) {

        this.name = name;
    }

    public ResourceType() {

    }

    public java.lang.String getName() {

        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public java.lang.String getId() {

        return id;
    }

    public void setId(java.lang.String id) {

        this.id = id;
    }

    public java.lang.String getDescription() {

        return description;
    }

    public void setDescription(java.lang.String description) {

        this.description = description;
    }
}
