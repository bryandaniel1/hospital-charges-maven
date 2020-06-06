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
import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.utility.ApiServiceUtility;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This ApiRegionalInpatientService implementation provides the functionality
 * required by inpatient REST service methods.
 *
 * @author Bryan Daniel
 */
public class ApiRegionalInpatientServiceImpl extends SimpleRegionalInpatientService implements ApiRegionalInpatientService {

    @Override
    public List<String> getAllStates() {
        return ApiServiceUtility.getAllStates();
    }

    @Override
    public List<DiagnosisRelatedGroup> getApiDrgsByRegion(String state, String city) {
        List<com.daniel.hospitalcharges.model.DiagnosisRelatedGroup> drgs = getDrgsByRegion(state, city);
        List<DiagnosisRelatedGroup> drgsToReturn = new ArrayList<>();
        if (drgs != null && !drgs.isEmpty()) {
            drgs.stream().map(result -> {
                DiagnosisRelatedGroup drg = new DiagnosisRelatedGroup();
                drg.setDrgId(result.getDrgId());
                drg.setDrgDefinition(result.getDrgDefinition());
                return drg;
            }).forEachOrdered(drg -> {
                drgsToReturn.add(drg);
            });
        }
        return drgsToReturn;
    }

    @Override
    public List<InpatientCharge> getApiChargeResults(String state, String city, Integer drgId) {
        List<InpatientComparisonResult> inpatientComparisonResults = getRegionalResults(state, city, drgId);
        List<InpatientCharge> inpatientChargesToReturn = new ArrayList<>();
        if (inpatientComparisonResults != null && !inpatientComparisonResults.isEmpty()) {
            inpatientComparisonResults.stream().map(result -> {
                InpatientCharge inpatientCharge = new InpatientCharge();
                inpatientCharge.setHospital(result.getProvider().getName());
                StringBuilder addressString = new StringBuilder();
                addressString.append(result.getProvider().getStreet());
                addressString.append(" ");
                addressString.append(result.getProvider().getCity());
                addressString.append(" ");
                addressString.append(result.getProvider().getState());
                addressString.append(" ");
                addressString.append(result.getProvider().getZipCode());
                inpatientCharge.setAddress(addressString.toString());
                inpatientCharge.setAvgCharges(new BigDecimal(result.getDrg().getAvgCharges()));
                inpatientCharge.setAvgPayments(new BigDecimal(result.getDrg().getAvgPayments()));
                inpatientCharge.setAvgMedicarePayments(new BigDecimal(result.getDrg().getAvgMedicarePayments()));
                return inpatientCharge;
            }).forEachOrdered(inpatientCharge -> {
                inpatientChargesToReturn.add(inpatientCharge);
            });
        }
        return inpatientChargesToReturn;
    }
}
