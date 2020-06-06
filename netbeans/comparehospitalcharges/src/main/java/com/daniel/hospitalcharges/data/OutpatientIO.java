package com.daniel.hospitalcharges.data;

import com.daniel.hospitalcharges.data.pool.OutpatientConnectionPool;
import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import com.daniel.hospitalcharges.model.Provider;
import com.daniel.hospitalcharges.data.utility.DatabaseUtility;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class retrieves provider data from the database.
 *
 * @author Bryan Daniel
 */
public class OutpatientIO {

    /**
     * The scale used for decimal numbers
     */
    public static final int SCALE = 2;

    // not called
    private OutpatientIO() {
    }

    /**
     * The logger for this class
     */
    @SuppressWarnings("FieldMayBeFinal")
    private static Logger logger = LogManager.getLogger(OutpatientIO.class);

    /**
     * This method retrieves the complete list of ambulatory payment
     * classification definitions.
     *
     * @return the list of APC definitions or null if an error occurs
     */
    public static List<AmbulatoryPaymentClassification> getAmbulatoryPaymentClassifications() {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        List<AmbulatoryPaymentClassification> ambulatoryPaymentClassifications = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("{CALL getAPCs(?)}");
            callableStatement.registerOutParameter(1, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                AmbulatoryPaymentClassification ambulatoryPaymentClassification = new AmbulatoryPaymentClassification();
                ambulatoryPaymentClassification.setApcId(resultSet.getInt("apc id"));
                ambulatoryPaymentClassification.setApcDefinition(resultSet.getString("apc definition"));
                if (!ambulatoryPaymentClassifications.contains(ambulatoryPaymentClassification)) {
                    ambulatoryPaymentClassifications.add(ambulatoryPaymentClassification);
                }
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred in getAmbulatoryPaymentClassifications method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return ambulatoryPaymentClassifications;
    }

    /**
     * This method returns a list of states associated with a given ambulatory
     * payment classification ID.
     *
     * @param apcId the APC ID
     * @return the list of states or null if an error occurs
     */
    public static List<String> getStates(int apcId) {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getStates(?, ?)}");
            callableStatement.setInt(1, apcId);
            callableStatement.registerOutParameter(2, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            List<String> states = new ArrayList<>();
            while (resultSet.next()) {
                String state = resultSet.getString("state");
                if (!states.contains(state)) {
                    states.add(state);
                }
            }
            return states;
        } catch (SQLException e) {
            logger.error("SQLException occurred in getStates method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
    }

    /**
     * This method returns a list of cities in the given state for an ambulatory
     * payment classification charge comparison.
     *
     * @param apcId the APC ID
     * @param providerState the state
     * @return the list of cities or null if an error occurs
     */
    public static List<String> getCitiesToCompare(int apcId, String providerState) {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getCitiesToCompare(?, ?, ?)}");
            callableStatement.setInt(1, apcId);
            callableStatement.setString(2, providerState);
            callableStatement.registerOutParameter(3, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            List<String> cities = new ArrayList<>();
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                if (!cities.contains(city)) {
                    cities.add(city);
                }
            }
            return cities;
        } catch (SQLException e) {
            logger.error("SQLException occurred in getCitiesToCompare method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
    }

    /**
     * This method returns a list of cities in the given state.
     *
     * @param providerState the state
     * @return the list of cities or null if an error occurs
     */
    public static List<String> getCities(String providerState) {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getCities(?, ?)}");
            callableStatement.setString(1, providerState);
            callableStatement.registerOutParameter(2, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            List<String> cities = new ArrayList<>();
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                if (!cities.contains(city)) {
                    cities.add(city);
                }
            }
            return cities;
        } catch (SQLException e) {
            logger.error("SQLException occurred in getCities method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
    }

    /**
     * This method retrieves a list of providers for the specified state and
     * city.
     *
     * @param state the state
     * @param city the city
     * @param apcId the APC ID
     * @return the list of providers or null if an error occurs
     */
    public static List<Provider> getProviders(String state, String city,
            int apcId) {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        List<Provider> providers = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("{CALL getProviders(?, ?, ?, ?)}");
            callableStatement.setInt(1, apcId);
            callableStatement.setString(2, city);
            callableStatement.setString(3, state);
            callableStatement.registerOutParameter(4, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                providers.add(provider);
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred in getProviders method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return providers;
    }

    /**
     * This method retrieves a list of ambulatory payment classification
     * definitions filtered by state and city.
     *
     * @param selectedState the state
     * @param selectedCity the city
     * @return the list of APC definitions or null if an error occurs
     */
    public static List<AmbulatoryPaymentClassification> getAmbulatoryPaymentClassificationsByRegion(String selectedState,
            String selectedCity) {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        List<AmbulatoryPaymentClassification> ambulatoryPaymentClassifications = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("{CALL getRegionalAPCs(?, ?, ?)}");
            callableStatement.setString(1, selectedCity);
            callableStatement.setString(2, selectedState);
            callableStatement.registerOutParameter(3, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                AmbulatoryPaymentClassification ambulatoryPaymentClassification = new AmbulatoryPaymentClassification();
                ambulatoryPaymentClassification.setApcId(resultSet.getInt("apc id"));
                ambulatoryPaymentClassification.setApcDefinition(resultSet.getString("apc definition"));
                ambulatoryPaymentClassifications.add(ambulatoryPaymentClassification);
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred in getAmbulatoryPaymentClassificationsByRegion method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return ambulatoryPaymentClassifications;
    }

    /**
     * This method retrieves a charge description by the ambulatory payment
     * classification ID and the provider ID.
     *
     * @param apcId the APC ID
     * @param providerId the provider ID
     * @return the APC charges or null if an error occurs
     */
    public static AmbulatoryPaymentClassification getCharges(int apcId, int providerId) {

        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        AmbulatoryPaymentClassification ambulatoryPaymentClassification = new AmbulatoryPaymentClassification();

        try {
            callableStatement = connection.prepareCall("{CALL getCharges(?, ?, ?)}");
            callableStatement.setInt(1, apcId);
            callableStatement.setInt(2, providerId);
            callableStatement.registerOutParameter(3, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                ambulatoryPaymentClassification.setAvgCharges(resultSet.getString("avg charges"));
                ambulatoryPaymentClassification.setAvgPayments(resultSet.getString("avg payments"));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                ambulatoryPaymentClassification.setAvgChargesPercentileRank(resultSet.getBigDecimal("avg charges percentile")
                        .setScale(SCALE, BigDecimal.ROUND_HALF_UP));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                ambulatoryPaymentClassification.setAvgPaymentsPercentileRank(resultSet.getBigDecimal("avg payments percentile")
                        .setScale(SCALE, BigDecimal.ROUND_HALF_UP));
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred in getCharges method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return ambulatoryPaymentClassification;
    }

    /**
     * This method returns a list of charge descriptions for a geographic
     * region.
     *
     * @param state the state
     * @param city the city
     * @param apcId the APC ID
     * @return the list of comparison results or null if an error occurs
     */
    public static List<OutpatientComparisonResult> getRegionalResults(String state,
            String city, int apcId) {
        List<OutpatientComparisonResult> results = new ArrayList<>();
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getRegionalCharges(?, ?, ?, ?)}");

            callableStatement.setInt(1, apcId);
            callableStatement.setString(2, city);
            callableStatement.setString(3, state);
            callableStatement.registerOutParameter(4, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                OutpatientComparisonResult outpatientComparisonResult = new OutpatientComparisonResult();
                AmbulatoryPaymentClassification ambulatoryPaymentClassification = new AmbulatoryPaymentClassification();
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                ambulatoryPaymentClassification.setApcId(resultSet.getInt("apc id"));
                ambulatoryPaymentClassification.setApcDefinition(resultSet.getString("apc definition"));
                ambulatoryPaymentClassification.setAvgCharges(resultSet.getString("avg charges"));
                ambulatoryPaymentClassification.setAvgPayments(resultSet.getString("avg payments"));
                outpatientComparisonResult.setProvider(provider);
                outpatientComparisonResult.setApc(ambulatoryPaymentClassification);
                results.add(outpatientComparisonResult);
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred in getRegionalResults method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return results;
    }
}
