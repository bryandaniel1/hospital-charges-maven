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

import java.math.BigDecimal;

/**
 * Holds inpatient charge information for the REST service.
 *
 * @author Bryan Daniel
 */
public class InpatientCharge {

    /**
     * The hospital name
     */
    private String hospital;

    /**
     * The hospital address
     */
    private String address;

    /**
     * The average charges for the procedure
     */
    private BigDecimal avgCharges;

    /**
     * The average payments for the procedure
     */
    private BigDecimal avgPayments;

    /**
     * The average Medicare payments for the procedure
     */
    private BigDecimal avgMedicarePayments;

    /**
     * Gets the hospital name.
     *
     * @return the value of hospital
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * Sets the hospital name.
     *
     * @param hospital the new value of hospital
     */
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    /**
     * Gets the hospital address.
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the hospital address.
     *
     * @param address the new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the average charges.
     *
     * @return the value of avgCharges
     */
    public BigDecimal getAvgCharges() {
        return avgCharges;
    }

    /**
     * Sets the average charges.
     *
     * @param avgCharges the new value of avgCharges
     */
    public void setAvgCharges(BigDecimal avgCharges) {
        this.avgCharges = avgCharges;
    }

    /**
     * Gets the average payments.
     *
     * @return the value of avgPayments
     */
    public BigDecimal getAvgPayments() {
        return avgPayments;
    }

    /**
     * Sets the average payments.
     *
     * @param avgPayments the new value of avgPayments
     */
    public void setAvgPayments(BigDecimal avgPayments) {
        this.avgPayments = avgPayments;
    }

    /**
     * Gets the average Medicare payments.
     *
     * @return the value of avgMedicarePayments
     */
    public BigDecimal getAvgMedicarePayments() {
        return avgMedicarePayments;
    }

    /**
     * Sets the average Medicare payments.
     *
     * @param avgMedicarePayments the new value of avgMedicarePayments
     */
    public void setAvgMedicarePayments(BigDecimal avgMedicarePayments) {
        this.avgMedicarePayments = avgMedicarePayments;
    }
}
