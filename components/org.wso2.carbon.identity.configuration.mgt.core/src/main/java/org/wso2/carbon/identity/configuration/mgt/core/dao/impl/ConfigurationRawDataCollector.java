package org.wso2.carbon.identity.configuration.mgt.core.dao.impl;

class ConfigurationRawDataCollector {

    private int tenantId;
    private String resourceId;
    private String resourceName;
    private String lastModified;
    private String resourceTypeName;
    private String resourceTypeDescription;
    private String attributeKey;

    public int getTenantId() {

        return tenantId;
    }

    public String getResourceId() {

        return resourceId;
    }

    public String getResourceName() {

        return resourceName;
    }

    public String getLastModified() {

        return lastModified;
    }

    public String getResourceTypeName() {

        return resourceTypeName;
    }

    public String getResourceTypeDescription() {

        return resourceTypeDescription;
    }

    public String getAttributeKey() {

        return attributeKey;
    }

    public String getAttributeValue() {

        return attributeValue;
    }

    public String getFileId() {

        return fileId;
    }

    private String attributeValue;
    private String fileId;

    public ConfigurationRawDataCollector(ConfigurationRawDataCollectorBuilder builder) {

        this.tenantId = builder.getTenantId();
        this.resourceId = builder.getResourceId();
        this.resourceName = builder.getResourceName();
        this.lastModified = builder.getLastModified();
        this.resourceTypeName = builder.getResourceTypeName();
        this.resourceTypeDescription = builder.getResourceTypeDescription();
        this.attributeKey = builder.getAttributeKey();
        this.attributeValue = builder.getAttributeValue();
        this.fileId = builder.getFileId();
    }

    public static class ConfigurationRawDataCollectorBuilder {

        private int tenantId;
        private String resourceId;
        private String resourceName;
        private String lastModified;
        private String resourceTypeName;
        private String resourceTypeDescription;
        private String attributeKey;

        int getTenantId() {

            return tenantId;
        }

        String getResourceId() {

            return resourceId;
        }

        String getResourceName() {

            return resourceName;
        }

        String getLastModified() {

            return lastModified;
        }

        String getResourceTypeName() {

            return resourceTypeName;
        }

        String getResourceTypeDescription() {

            return resourceTypeDescription;
        }

        String getAttributeKey() {

            return attributeKey;
        }

        String getAttributeValue() {

            return attributeValue;
        }

        String getFileId() {

            return fileId;
        }

        private String attributeValue;
        private String fileId;

        public ConfigurationRawDataCollectorBuilder setTenantId(int tenantId) {

            this.tenantId = tenantId;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setResourceId(String resourceId) {

            this.resourceId = resourceId;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setResourceName(String resourceName) {

            this.resourceName = resourceName;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setLastModified(String lastModified) {

            this.lastModified = lastModified;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setResourceTypeName(String resourceTypeName) {

            this.resourceTypeName = resourceTypeName;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setResourceTypeDescription(String resourceTypeDescription) {

            this.resourceTypeDescription = resourceTypeDescription;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setAttributeKey(String attributeKey) {

            this.attributeKey = attributeKey;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setAttributeValue(String attributeValue) {

            this.attributeValue = attributeValue;
            return this;
        }

        public ConfigurationRawDataCollectorBuilder setFileId(String fileId) {

            this.fileId = fileId;
            return this;
        }

        public ConfigurationRawDataCollector build() {

            return new ConfigurationRawDataCollector(this);
        }
    }
}
