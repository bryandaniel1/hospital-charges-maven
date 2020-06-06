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

import com.daniel.hospitalcharges.api.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.api.model.InpatientCharge;
import com.daniel.hospitalcharges.service.ServiceProvider;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Handles REST service requests for inpatient charge data.
 *
 * @author Bryan Daniel
 */
@Path("/inpatient")
public class InpatientResource {

    /**
     * Returns the list of all states in the United States.
     *
     * @return the list of all states
     */
    @GET
    @Path("/states")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStates() {
        return ServiceProvider.getApiRegionalInpatientService().getAllStates();
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
        List<String> cities = ServiceProvider.getApiRegionalInpatientService().getCities(stateAbbreviation);
        if (cities == null || cities.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cities, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Returns a list of diagnosis-related group descriptions for the given
     * state and city.
     *
     * @param stateAbbreviation the state
     * @param cityName the city
     * @return the list of DRGs
     */
    @GET
    @Path("/drgs/{stateAbbreviation}/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrgs(@PathParam("stateAbbreviation") String stateAbbreviation, @PathParam("cityName") String cityName) {
        List<DiagnosisRelatedGroup> drgs = ServiceProvider.getApiRegionalInpatientService()
                .getApiDrgsByRegion(stateAbbreviation, cityName);
        if (drgs == null || drgs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(drgs, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Returns a list of inpatient charges associated with the given state,
     * city, and diagnosis-related group.
     *
     * @param stateAbbreviation the state
     * @param cityName the city
     * @param drgId the DRG identifier
     * @return the list of inpatient charges
     */
    @GET
    @Path("/charges/{stateAbbreviation}/{cityName}/{drgId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharges(@PathParam("stateAbbreviation") String stateAbbreviation, @PathParam("cityName") String cityName,
            @PathParam("drgId") int drgId) {
        List<InpatientCharge> inpatientCharges = ServiceProvider.getApiRegionalInpatientService()
                .getApiChargeResults(stateAbbreviation, cityName, drgId);
        if (inpatientCharges == null || inpatientCharges.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(inpatientCharges, MediaType.APPLICATION_JSON).build();
    }
}
