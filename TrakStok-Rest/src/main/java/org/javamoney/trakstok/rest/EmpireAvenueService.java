/*
 * Copyright 2014 JUGChennai.
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
package org.javamoney.trakstok.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.agorava.api.service.OAuthLifeCycleService;
import org.agorava.empireavenue.EmpireAvenue;
import org.agorava.empireavenue.service.ProfileService;

/**
 *
 * @author rajmahendrahegde
 */
@Path("empireavenue")
public class EmpireAvenueService {

    @Inject
    @EmpireAvenue
    protected ProfileService profileService;

    @Inject
    OAuthLifeCycleService lifeCycleService;

    @GET
    @Path("/startDance")
    public String startDanceFor(@PathParam("service") String service) {
        return lifeCycleService.startDanceFor(service);
    }

}
