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

package org.javamoney.trakstok.rest.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.agorava.empireavenue.model.ProfileInfo;
import org.javamoney.trakstok.model.Currency;
import org.javamoney.trakstok.model.CurrencyList;

/**
 * 
 * @author rajmahendrahegde
 */

@SuppressWarnings("serial")
@Named
@SessionScoped
public class EmpireAvenueClient implements Serializable {
    static final String REST_SERVICE_ENDPOINT = "http://localhost:8080/trakstok/api/";
    static final String START_DANCE_SERVICE = "empireavenue/startDance";
    static final String SUPPORTED_CURRENCY_SERVICE = "exchange/supportedcurrencies";
    static final String PROFILE_INFO_SERVICE = "empireavenue/profileInfo";
    @SuppressWarnings("unused")
    private List<Currency> allSupportedCurrency;

    static final Client client = ClientBuilder.newClient();

    public String getStartDanceUrl() {

        Response res = client.target(buildUrl(START_DANCE_SERVICE)).request("text/plain").get();
        String message = res.readEntity(String.class);
        return message;
    }

    public List<Currency> getAllSupportedCurrency() {
    	List<Currency> list = client.target(buildUrl(SUPPORTED_CURRENCY_SERVICE)).request("application/json").get(new GenericType<List<Currency>>(){});
        return list;

    }

    private String buildUrl(String servicePoint) {
        return REST_SERVICE_ENDPOINT + servicePoint;
    }

    public void setAllSupportedCurrency(List<Currency> allSupportedCurrency) {
        this.allSupportedCurrency = allSupportedCurrency;
    }
    
    public ProfileInfo getProfileInfo() {
    	ProfileInfo profileInfo = client.target(buildUrl(PROFILE_INFO_SERVICE)).request("application/json").get(new GenericType<ProfileInfo>(){});
    	return profileInfo;    	
    }

}
