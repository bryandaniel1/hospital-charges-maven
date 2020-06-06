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
package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.api.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.api.model.InpatientCharge;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods used by the REST service for retrieving data associated with
 * regional inpatient charges.
 *
 * @author Bryan Daniel
 */
public interface ApiRegionalInpatientService extends RegionalInpatientService {

    /**
     * Returns the list of names for all states in the United States.
     *
     * @return the list of all states
     */
    public List<String> getAllStates();

    /**
     * Returns a list of diagnosis-related groups based on the given city and
     * state.
     *
     * @param state the given state
     * @param city the given city
     * @return the list of diagnosis-related groups
     */
    public List<DiagnosisRelatedGroup> getApiDrgsByRegion(String state, String city);

    /**
     * Fetches the list of inpatient charges for the given state, city, and
     * diagnosis-related group.
     *
     * @param state the state
     * @param city the city
     * @param drgId the identifier for the DRG
     * @return the list of inpatient charges
     */
    public List<InpatientCharge> getApiChargeResults(String state, String city, Integer drgId);
}
