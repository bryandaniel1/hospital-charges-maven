/*
 * Copyright 2020 Bryan Daniel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.daniel.hospitalcharges.api.model;

/**
 * Holds an ambulatory payment classification description for the REST service.
 *
 * @author Bryan Daniel
 */
public class AmbulatoryPaymentClassification {

    /**
     * The APC ID
     */
    private int apcId;
    /**
     * The APC definition
     */
    private String apcDefinition;

    /**
     * Gets the ambulatory payment classification identifier.
     *
     * @return the value of apcId
     */
    public int getApcId() {
        return apcId;
    }

    /**
     * Sets the ambulatory payment classification identifier.
     *
     * @param apcId the new value of apcId
     */
    public void setApcId(int apcId) {
        this.apcId = apcId;
    }

    /**
     * Gets the ambulatory payment classification definition.
     *
     * @return the value of apcDefinition
     */
    public String getApcDefinition() {
        return apcDefinition;
    }

    /**
     * Sets the ambulatory payment classification definition.
     *
     * @param apcDefinition the new value of apcDefinition
     */
    public void setApcDefinition(String apcDefinition) {
        this.apcDefinition = apcDefinition;
    }
}
