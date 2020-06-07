package com.daniel.hospitalcharges.service;

/**
 * This class constructs and returns service implementations.
 *
 * @author Bryan Daniel
 */
public class ServiceProvider {

    /**
     * The service for comparing regional inpatient charges
     */
    private static RegionalInpatientService regionalInpatientService = null;

    /**
     * The service for comparing regional outpatient charges
     */
    private static RegionalOutpatientService regionalOutpatientService = null;

    /**
     * The service for comparing two inpatient charges
     */
    private static InpatientComparisonService inpatientComparisonService = null;

    /**
     * The service for comparing two outpatient charges
     */
    private static OutpatientComparisonService outpatientComparisonService = null;

    /**
     * The API service for comparing regional inpatient charges
     */
    private static ApiRegionalInpatientService apiRegionalInpatientService = null;

    /**
     * The API service for comparing regional outpatient charges
     */
    private static ApiRegionalOutpatientService apiRegionalOutpatientService = null;

    /**
     * This block constructs the service implementations.
     */
    static {
        regionalInpatientService = new SimpleRegionalInpatientService();
        regionalOutpatientService = new SimpleRegionalOutpatientService();
        inpatientComparisonService = new SimpleInpatientComparisonService();
        outpatientComparisonService = new SimpleOutpatientComparisonService();
        apiRegionalInpatientService = new ApiRegionalInpatientServiceImpl();
        apiRegionalOutpatientService = new ApiRegionalOutpatientServiceImpl();
    }

    // not instantiated
    private ServiceProvider() {
    }

    /**
     * This method returns a regional inpatient service implementation.
     *
     * @return the regional inpatient service
     */
    public static RegionalInpatientService getRegionalInpatientService() {
        return regionalInpatientService;
    }

    /**
     * This method returns a regional outpatient service implementation.
     *
     * @return the regional outpatient service
     */
    public static RegionalOutpatientService getRegionalOutpatientService() {
        return regionalOutpatientService;
    }

    /**
     * This method returns an inpatient comparison service implementation.
     *
     * @return the inpatient comparison service
     */
    public static InpatientComparisonService getInpatientComparisonService() {
        return inpatientComparisonService;
    }

    /**
     * This method returns an outpatient comparison service implementation.
     *
     * @return the outpatient comparison service
     */
    public static OutpatientComparisonService getOutpatientComparisonService() {
        return outpatientComparisonService;
    }

    /**
     * This method returns a regional inpatient service implementation for the
     * REST service.
     *
     * @return the regional inpatient service
     */
    public static ApiRegionalInpatientService getApiRegionalInpatientService() {
        return apiRegionalInpatientService;
    }

    /**
     * This method returns a regional outpatient service implementation for the
     * REST service.
     *
     * @return the regional outpatient service
     */
    public static ApiRegionalOutpatientService getApiRegionalOutpatientService() {
        return apiRegionalOutpatientService;
    }
}
