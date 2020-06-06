package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import com.daniel.hospitalcharges.model.Provider;
import java.util.List;

/**
 * This interface contains the methods for retrieving data associated with
 * comparisons of outpatient charges.
 * 
 * @author Bryan Daniel
 */
public interface OutpatientComparisonService {
    
    /**
     * Returns the list of all ambulatory payment classifications
     *
     * @return the APCs
     */
    public List<AmbulatoryPaymentClassification> getApcs();

    /**
     * Returns the list of states based on the given APC ID
     *
     * @param apcId the APC ID
     * @return the list of states
     */
    public List<String> getStates(int apcId);

    /**
     * Returns the list of cities to compare based on the given DRG ID and state
     *
     * @param apcId the APC ID
     * @param state the state
     * @return the list of cities
     */
    public List<String> getCitiesToCompare(int apcId, String state);

    /**
     * Returns the list of providers based on the given state, city, and DRG ID
     *
     * @param state the state
     * @param city the city
     * @param apcId the APC ID
     * @return the list of providers
     */
    public List<Provider> getProviders(String state, String city, int apcId);

    /**
     * Returns the information on a hospital charge based on the provider and APC
     *
     * @param providers the list of providers
     * @param providerId the provider ID
     * @param apcs the list of APCs
     * @param apcId the APC ID
     * @return the result
     */
    public OutpatientComparisonResult getResult(List<Provider> providers, int providerId, 
            List<AmbulatoryPaymentClassification> apcs, int apcId);
}
