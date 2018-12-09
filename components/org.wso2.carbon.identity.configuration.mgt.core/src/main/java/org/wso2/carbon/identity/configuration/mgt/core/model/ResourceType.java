package org.wso2.carbon.identity.configuration.mgt.core.model;

/**
 * A model class representing a resource type.
 */
public class ResourceType {

    private String name;

    private String id;

    private String description;

    public ResourceType(String name, String id, String description) {

        this.name = name;
        this.id = id;
        this.description = description;
    }

    public ResourceType() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
