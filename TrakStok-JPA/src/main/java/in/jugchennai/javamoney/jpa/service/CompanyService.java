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
package in.jugchennai.javamoney.jpa.service;

import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsCurrency;
import in.jugchennai.javamoney.jpa.service.entity.TsStockInflection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class CompanyService {

    private static final Logger log = Logger.getGlobal();
    private static EntityManagerFactory emFactory;
    private static EntityManager eManager;

    private CompanyService() {
    }

    static {
        emFactory = Persistence.createEntityManagerFactory("trakStokPU");
        eManager = emFactory.createEntityManager();
    }

    public static boolean addCompany(TsCompany company) {

        if (findBySymbol("SUNM").isEmpty()) {
            return false;
        }
        eManager.getTransaction().begin();
        eManager.persist(company);
        eManager.getTransaction().commit();
        return true;
    }
    
    public static Collection<TsCompany> findAllCompanies() {
        return (Collection<TsCompany>) eManager.createNamedQuery("TsCompany.findAll")
                .getResultList();
    }

    public static Collection<TsCompany> findBySymbol(String symbol) {
        return (Collection<TsCompany>) eManager.createNamedQuery("TsCompany.findBySymbol")
                .setParameter("symbol", symbol)
                .getResultList();
    }
    
    public static Collection<TsCompany> listBySymbol(String symbol) {
        return (Collection<TsCompany>) eManager.createNamedQuery("TsCompany.listBySymbol")
                .setParameter("symbol", symbol+"%")
                .getResultList();
    }
    
    public static LinkedHashMap<Object, Number> getTrendMap(String symbol, TrendFrequency frequency, Date fromDate, Date toDate) {
        switch(frequency){
            case DAILY :
                return getDailyTrend(symbol,fromDate,toDate);            
            default:
                return getDailyTrend(symbol,fromDate,toDate);
        }        
    }
    
    public static List<TsCurrency> getCurrencyList() {
        List<TsCurrency> currencyList = new ArrayList<TsCurrency>();
        Query qry = eManager.createNamedQuery("TsCurrency.findAll");
        currencyList = qry.getResultList();
        log.info("currency list is "+currencyList.toString());
        return currencyList;
    }
    
    public static List<TsCurrency> getCurrencyList(String currencyCode) {
        List<TsCurrency> currencyList = new ArrayList<TsCurrency>();
        Query qry = eManager.createNamedQuery("TsCurrency.searchByCurrencyCode");
        qry.setParameter("currencyCode", currencyCode+"%");
        currencyList = qry.getResultList();
        log.info("currency list is "+currencyList.toString());
        return currencyList;
    }

    private static LinkedHashMap<Object, Number> getDailyTrend(String symbol, Date fromDate, Date toDate) {
        LinkedHashMap<Object, Number> trendMap = new LinkedHashMap<Object, Number>();
        Query qry = eManager.createNamedQuery("TsStockInflection.dailyTrendForSymbol");
        qry.setParameter("symbol", symbol);
        qry.setParameter("fromdate", fromDate);
        qry.setParameter("todate", toDate);
        Collection<TsStockInflection> trend = qry.getResultList();        
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM");
        SimpleDateFormat sfh = new SimpleDateFormat("HH");
        for(TsStockInflection t : trend){           
            //Get the last entry of the day which is at 5PM, so harcoded the 5 PM
            //If we could enable this criteria in query, it is well and fine
            if(sfh.format(t.getDatetime()).equals("17")){
                trendMap.put(sf.format(t.getDatetime()), t.getAmount());
            }            
        }
        log.info(trendMap.toString());
        return trendMap;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        eManager.close();
        emFactory.close();
    }
}
