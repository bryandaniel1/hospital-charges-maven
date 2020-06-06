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
 * Holds a diagnosis-related group description for the REST service.
 *
 * @author Bryan Daniel
 */
public class DiagnosisRelatedGroup {

    /**
     * The DRG ID
     */
    private int drgId;
    /**
     * The DRG definition
     */
    private String drgDefinition;

    /**
     * Gets the diagnosis-related group identifier.
     *
     * @return the value of drgId
     */
    public int getDrgId() {
        return drgId;
    }

    /**
     * Sets the diagnosis-related group identifier.
     *
     * @param drgId the new value of drgId
     */
    public void setDrgId(int drgId) {
        this.drgId = drgId;
    }

    /**
     * Gets the diagnosis-related group definition.
     *
     * @return the value of drgDefinition
     */
    public String getDrgDefinition() {
        return drgDefinition;
    }

    /**
     * Sets the diagnosis-related group definition.
     *
     * @param drgDefinition the new value of drgDefinition
     */
    public void setDrgDefinition(String drgDefinition) {
        this.drgDefinition = drgDefinition;
    }
}
