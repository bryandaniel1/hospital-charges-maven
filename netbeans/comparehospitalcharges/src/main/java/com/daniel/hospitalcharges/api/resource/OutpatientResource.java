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
package com.daniel.hospitalcharges.api.resource;

import com.daniel.hospitalcharges.api.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.api.model.OutpatientCharge;
import com.daniel.hospitalcharges.service.ServiceProvider;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Handles REST service requests for outpatient charge data.
 *
 * @author Bryan Daniel
 */
@Path("/outpatient")
public class OutpatientResource {

    /**
     * Returns the list of all states in the United States.
     *
     * @return the list of all states
     */
    @GET
    @Path("/states")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStates() {
        return ServiceProvider.getApiRegionalOutpatientService().getAllStates();
    }

    /**
     * Returns the list of all cities for the given state.
     *
     * @param stateAbbreviation the state abbreviation
     * @return the list of all cities with charge data
     */
    @GET
    @Path("/cities/{stateAbbreviation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCities(@PathParam("stateAbbreviation") String stateAbbreviation) {
        List<String> cities = ServiceProvider.getApiRegionalOutpatientService().getCities(stateAbbreviation);
        if (cities == null || cities.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cities, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Returns a list of ambulatory payment classification descriptions for the
     * given state and city.
     *
     * @param stateAbbreviation the state
     * @param cityName the city
     * @return the list of APCs
     */
    @GET
    @Path("/apcs/{stateAbbreviation}/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrgs(@PathParam("stateAbbreviation") String stateAbbreviation, @PathParam("cityName") String cityName) {
        List<AmbulatoryPaymentClassification> apcs = ServiceProvider.getApiRegionalOutpatientService()
                .getApiApcsByRegion(stateAbbreviation, cityName);
        if (apcs == null || apcs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(apcs, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Returns a list of inpatient charges associated with the given state,
     * city, and ambulatory payment classification.
     *
     * @param stateAbbreviation the state
     * @param cityName the city
     * @param apcId the APC identifier
     * @return the list of inpatient charges
     */
    @GET
    @Path("/charges/{stateAbbreviation}/{cityName}/{apcId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharges(@PathParam("stateAbbreviation") String stateAbbreviation, @PathParam("cityName") String cityName,
            @PathParam("apcId") int apcId) {
        List<OutpatientCharge> inpatientCharges = ServiceProvider.getApiRegionalOutpatientService()
                .getApiChargeResults(stateAbbreviation, cityName, apcId);
        if (inpatientCharges == null || inpatientCharges.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(inpatientCharges, MediaType.APPLICATION_JSON).build();
    }
}
