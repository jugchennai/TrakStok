/*
 * Copyright 2013 JUGChennai.
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
package in.jugchennai.javamoney.trakstok.rest;

import in.jugchennai.javamoney.jpa.service.CompanyService;
import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsCurrency;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@Path("/company")
@RequestScoped
public class CompanyResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<TsCompany> list(@QueryParam("symbol") String symbol, @DefaultValue("false") @QueryParam("listBySmbol") boolean listBySymbol) {
        if (symbol != null && listBySymbol) {
            // list by symbol
            return CompanyService.listBySymbol(symbol);
        } else if (symbol != null && !listBySymbol) {
            // find by symbol
            return CompanyService.findBySymbol(symbol);
        } else {
            // Default
            return CompanyService.findAllCompanies();
        }
    }
        
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<TsCompany> list(@PathParam("id") int id) {
        return CompanyService.findAllCompanies();
    }
    
    @GET
    @Path("/trendMap")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public LinkedHashMap<Object, Number> trendMap(@QueryParam("symbol") String symbol, @QueryParam("frequency") String frequency, @QueryParam("fromDate") Date fromDate, @QueryParam("toDate") Date toDate) {
        LinkedHashMap<Object, Number> trendMap = null;
        if (symbol != null && frequency != null && fromDate != null && toDate != null) {
            TrendFrequency trendFrequency = TrendFrequency.valueOf(frequency);
            trendMap = CompanyService.getTrendMap(symbol, trendFrequency, fromDate, toDate);
        }
        return trendMap;
    }
    
    @GET
    @Path("/currencyList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<TsCurrency> currencyList(@QueryParam("currencyCode") String currencyCode) {
        List<TsCurrency> currencyList = CompanyService.getCurrencyList(currencyCode);
        return currencyList;
    }
    
//    @GET
//    @Path("/dailyTrend")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
//    public Collection<TsCompany> dailyTrend(@QueryParam("symbol") String symbol, @QueryParam("fromDate") Date fromDate, @QueryParam("toDate") Date toDate) {
//        return CompanyService.findAllCompanies();
//    }
    
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response add(TsCompany company) {
        boolean status = CompanyService.addCompany(company);
        if (status) {
            return Response.status(200).build();
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).entity("Company is not added").build();
    }
        
    @PUT
    @Consumes (MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") int id, TsCompany company) {
        // TODO: need to be implemented
        return Response.status(200).build();
    }
            
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        // TODO: need to be implemented
        return Response.status(200).build();
    }
}
