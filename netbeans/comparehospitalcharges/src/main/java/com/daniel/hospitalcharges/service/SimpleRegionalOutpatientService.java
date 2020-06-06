package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.data.OutpatientIO;
import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import java.io.Serializable;
import java.util.List;

/**
 * This regional outpatient service implementation contains the methods for
 * retrieving data associated with regional outpatient charges.
 *
 * @author Bryan Daniel
 */
public class SimpleRegionalOutpatientService implements RegionalOutpatientService, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -7560541261409885996L;

    /**
     * Returns a list of cities based on the given state
     *
     * @param state the given state
     * @return the list of cities
     */
    @Override
    public List<String> getCities(String state) {
        return OutpatientIO.getCities(state);
    }

    /**
     * Returns a list of ambulatory payment classifications based on the given
     * city and state
     *
     * @param state the given state
     * @param city the given city
     * @return the list of ambulatory payment classifications
     */
    @Override
    public List<AmbulatoryPaymentClassification> getApcsByRegion(String state, String city) {
        return OutpatientIO.getAmbulatoryPaymentClassificationsByRegion(state, city);
    }

    /**
     * Returns a list of regional results for comparison
     *
     * @param state the given state
     * @param city the given city
     * @param apcId the given APC ID
     * @return the list of results
     */
    @Override
    public List<OutpatientComparisonResult> getRegionalResults(String state, String city, Integer apcId) {
        return OutpatientIO.getRegionalResults(state, city, apcId);
    }
}
