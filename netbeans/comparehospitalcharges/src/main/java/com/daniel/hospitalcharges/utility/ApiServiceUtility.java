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
package com.daniel.hospitalcharges.utility;

import java.util.Arrays;
import java.util.List;

/**
 * Provides basic reusable functionality for RESt service classes.
 *
 * @author Bryan Daniel
 */
public class ApiServiceUtility {

    /**
     * Returns the list of names for all states in the United States.
     *
     * @return the list of all states
     */
    public static List<String> getAllStates() {
        return Arrays.asList("AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL",
                "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI",
                "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH",
                "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI",
                "WV", "WY");
    }
}
