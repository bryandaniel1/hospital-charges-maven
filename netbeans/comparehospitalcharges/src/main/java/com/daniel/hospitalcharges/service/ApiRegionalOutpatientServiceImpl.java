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

import com.daniel.hospitalcharges.api.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.api.model.OutpatientCharge;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import com.daniel.hospitalcharges.utility.ApiServiceUtility;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This ApiRegionalOutpatientService implementation provides the functionality
 * required by outpatient REST service methods.
 *
 * @author Bryan Daniel
 */
public class ApiRegionalOutpatientServiceImpl extends SimpleRegionalOutpatientService implements ApiRegionalOutpatientService {

    @Override
    public List<String> getAllStates() {
        return ApiServiceUtility.getAllStates();
    }

    @Override
    public List<AmbulatoryPaymentClassification> getApiApcsByRegion(String state, String city) {
        List<com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification> apcs = getApcsByRegion(state, city);
        List<AmbulatoryPaymentClassification> apcsToReturn = new ArrayList<>();
        if (apcs != null & !apcs.isEmpty()) {
            apcs.stream().map(result -> {
                AmbulatoryPaymentClassification apc = new AmbulatoryPaymentClassification();
                apc.setApcId(result.getApcId());
                apc.setApcDefinition(result.getApcDefinition());
                return apc;
            }).forEachOrdered(apc -> {
                apcsToReturn.add(apc);
            });
        }
        return apcsToReturn;
    }

    @Override
    public List<OutpatientCharge> getApiChargeResults(String state, String city, Integer apcId) {
        List<OutpatientComparisonResult> outpatientComparisonResults = getRegionalResults(state, city, apcId);
        List<OutpatientCharge> outpatientChargesToReturn = new ArrayList<>();
        if (outpatientComparisonResults != null && !outpatientComparisonResults.isEmpty()) {
            outpatientComparisonResults.stream().map(result -> {
                OutpatientCharge outpatientCharge = new OutpatientCharge();
                outpatientCharge.setHospital(result.getProvider().getName());
                StringBuilder addressString = new StringBuilder();
                addressString.append(result.getProvider().getStreet());
                addressString.append(" ");
                addressString.append(result.getProvider().getCity());
                addressString.append(" ");
                addressString.append(result.getProvider().getState());
                addressString.append(" ");
                addressString.append(result.getProvider().getZipCode());
                outpatientCharge.setAddress(addressString.toString());
                outpatientCharge.setAvgCharges(new BigDecimal(result.getApc().getAvgCharges()));
                outpatientCharge.setAvgPayments(new BigDecimal(result.getApc().getAvgPayments()));
                return outpatientCharge;
            }).forEachOrdered(outpatientCharge -> {
                outpatientChargesToReturn.add(outpatientCharge);
            });
        }
        return outpatientChargesToReturn;
    }

}
