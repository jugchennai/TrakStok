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
package in.jugchennai.javamoney.jpa;

import in.jugchennai.javamoney.jpa.service.CompanyService;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import org.junit.After;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class CompanyTest {

    public CompanyTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Ignore
    @Test
    public void testAddNewCompany() {

        TsCompany theCompany = new TsCompany();
        theCompany.setDisplayname("Sun Microsystems");
        theCompany.setSymbol("SUN");

        assertTrue("User or Company not found.", CompanyService.addCompany(theCompany));
    }
    
    @Ignore
    @Test
    public void tesGetCurrencyList() {

        String currencyCode = "US";
        System.out.println(CompanyService.getCurrencyList(currencyCode));
    }
}