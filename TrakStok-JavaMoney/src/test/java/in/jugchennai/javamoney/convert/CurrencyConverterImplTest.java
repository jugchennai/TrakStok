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
package in.jugchennai.javamoney.convert;

import javax.money.Money;
import javax.money.MoneyCurrency;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Balaji T
 */
public class CurrencyConverterImplTest {
    
    public CurrencyConverterImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doConvert method, of class CurrencyConverterImpl.
     */
    @Ignore
    @Test
    public void testDoConvert() {
        System.out.println("doConvert");
        Money sourceMoney = new Money(MoneyCurrency.of("INR"), 100);
        CurrencyConverterImpl instance = new CurrencyConverterImpl(MoneyCurrency.of("INR"), MoneyCurrency.of("USD"));
        Money expResult = null;
        Money result = instance.doConvert(sourceMoney);
        //assertEquals(expResult, result);
        System.out.println(result);
        
    }
}