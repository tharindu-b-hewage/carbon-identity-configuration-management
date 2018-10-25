/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.configuration.mgt.core;

import org.wso2.carbon.identity.configuration.mgt.core.model.Attribute;
import org.wso2.carbon.identity.configuration.mgt.core.model.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Manager service implementation.
 */
public class ConfigurationManagerImpl implements ConfigurationManager {

    private Configuration sampleConfiguration;

    /**
     * Initialize with a sample configuration
     */
    public ConfigurationManagerImpl() {

        List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(new Attribute("mail.from", "abc@gmail.com"));
        attributes.add(new Attribute("mail.host", "smtp.gmai.com"));

        this.sampleConfiguration = new Configuration("email", attributes);
    }

    @Override
    public Configuration getConfiguration() {

        return this.sampleConfiguration;
    }

    public void setSampleConfiguration(Configuration sampleConfiguration) {

        this.sampleConfiguration = sampleConfiguration;
    }
}
