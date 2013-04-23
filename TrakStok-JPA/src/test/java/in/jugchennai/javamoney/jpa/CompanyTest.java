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
import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
    
    @Test
    public void testGetDailyTrend() {

        String symbol = "GOOG";
        
        System.out.println(CompanyService.getTrendMap(symbol,TrendFrequency.DAILY, getDefaultFromDate(), getDefaultToDate()));
    }
    
    private Date getDefaultFromDate(){        
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();  
        calendar.add(calendar.DAY_OF_MONTH, -(Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()))-1));
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sf.parse(sf.format(calendar.getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(CompanyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Date getDefaultToDate(){        
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();                
        return calendar.getTime();
    }
}