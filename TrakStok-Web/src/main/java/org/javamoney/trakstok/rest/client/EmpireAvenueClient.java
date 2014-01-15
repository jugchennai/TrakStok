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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
/**
 *
 * @author rajmahendrahegde
 */
public class EmpireAvenueClient {
    static final String REST_SERVICE_ENDPOINT = "http://localhost:8000/trakstok/api/";
    static final String START_DANCE_SERVICE="";
    
    Client client = ClientBuilder.newClient();
    
}
