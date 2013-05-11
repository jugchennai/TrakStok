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
package in.jugchennai.javamoney.trakstok.bean;

import in.jugchennai.javamoney.convert.CurrencyConverter;
import in.jugchennai.javamoney.convert.CurrencyConverterImpl;
import in.jugchennai.javamoney.jpa.service.CompanyService;
import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsCurrency;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.money.Money;
import javax.money.MoneyCurrency;
import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@ManagedBean
public class CompanyBean extends TSBaseFormBean {
    
    private Integer companyid;
    private String displayname;
    private String symbol;
    private String anotherCompanySymbol;
    private String targetCurrency;
    private static final String SOURCE_CURRENCY_CODE = "USD";

    public CompanyBean() {
        logger = Logger.getLogger(CompanyBean.class);
    }

    public CompanyBean(Integer companyid, String displayname, String symbol) {
        this.companyid = companyid;
        this.displayname = displayname;
        this.symbol = symbol;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public String getAnotherCompanySymbol() {
        return anotherCompanySymbol;
    }

    public void setAnotherCompanySymbol(String anotherCompanySymbol) {
        this.anotherCompanySymbol = anotherCompanySymbol;
    }       

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
    
    public Collection<TsCompany> getAllCompanies() {
        return (Collection<TsCompany>)CompanyService.findAllCompanies();
    }

    public Collection<TsCompany> listBySymbol(String symbol){
        logger.info("Searching for the symbol.." + symbol);
        Collection<TsCompany> companyList = CompanyService.listBySymbol(symbol);
        logger.info(companyList);
        return companyList;
    }
    
    public CartesianChartModel getTrendModel(){
        CartesianChartModel trendModel = new CartesianChartModel();
        logger.info("Symbol "+getSymbol());
        logger.info("From Date "+getDefaultFromDate());
        logger.info("To Date "+getDefaultToDate());
        logger.info("Currency "+getTargetCurrency());
        String symbol = null;
        if(getSymbol() != null && !getSymbol().equals("")){
               symbol = getSymbol();          
        }else{
            for (TsCompany company : (Collection<TsCompany>)CompanyService.findAllCompanies()){
                symbol = company.getSymbol();
                if(symbol == null || symbol.equals("")){
                    continue;
                }
                break;
            }
        }
        String targetCurrencyCode = SOURCE_CURRENCY_CODE;
        if(getTargetCurrency() != null && !getTargetCurrency().equals("")){
            targetCurrencyCode = getTargetCurrency();
        }
        ChartSeries scrip = new ChartSeries(symbol+" in "+targetCurrencyCode);
        scrip.setData(getTrendMap(symbol, TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate(),targetCurrencyCode));        
        trendModel.addSeries(scrip);
        
        return trendModel;
    }
    
    public String compare(){
        return "compare";
    }
    
    public CartesianChartModel getCompareTrendModel(){
        CartesianChartModel trendModel = new CartesianChartModel();
        logger.debug("Symbol "+getSymbol());
        logger.debug("From Date "+getDefaultFromDate());
        logger.debug("To Date "+getDefaultToDate());
        String symbol = null;
        if(getSymbol() != null && !getSymbol().equals("")){
               symbol = getSymbol();          
        }else{
            for (TsCompany company : (Collection<TsCompany>)CompanyService.findAllCompanies()){
                symbol = company.getSymbol();
                if(symbol == null || symbol.equals("")){
                    continue;
                }
                break;
            }
        }        
        String targetCurrencyCode = SOURCE_CURRENCY_CODE;
        if(getTargetCurrency() != null && !getTargetCurrency().equals("")){
            targetCurrencyCode = getTargetCurrency();
        }
        ChartSeries scrip = new ChartSeries(symbol+" in "+ targetCurrencyCode);
        scrip.setData(getTrendMap(symbol, TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate(), targetCurrencyCode));        
        trendModel.addSeries(scrip);
        
        logger.debug("Another symbol "+getAnotherCompanySymbol());        
        if(getAnotherCompanySymbol() != null && !getAnotherCompanySymbol().equals("")){
            ChartSeries anotherScrip = new ChartSeries(getAnotherCompanySymbol()+" in "+targetCurrencyCode);
            anotherScrip.setData(getTrendMap(getAnotherCompanySymbol(), TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate(), targetCurrencyCode));        
            trendModel.addSeries(anotherScrip);
        }
        
        logger.debug(trendModel.getSeries());
        return trendModel;
    }
    
    public List<TsCurrency> getCurrencyList(String currencyCode){
        logger.info("currency code is "+currencyCode);
        return CompanyService.getCurrencyList(currencyCode);
    }

    private LinkedHashMap<Object, Number> getTrendMap(String symbol, TrendFrequency frequency, Date fromDate, Date toDate, String targetCurrencyCode) {    
      
        LinkedHashMap<Object, Number> result = CompanyService.getTrendMap(symbol,frequency,fromDate,toDate);                
        if(!targetCurrencyCode.equals(SOURCE_CURRENCY_CODE)){
            CurrencyConverter converter = new CurrencyConverterImpl(MoneyCurrency.of(SOURCE_CURRENCY_CODE), MoneyCurrency.of(targetCurrencyCode));
            LinkedHashMap<Object, Number> returnResult = new LinkedHashMap<>();
        for(Object key : result.keySet()){
            returnResult.put(key, (converter.convert(Money.of(SOURCE_CURRENCY_CODE, result.get(key)))).doubleValue());
        }
            return returnResult;
        }
        return result;
    }
    
    private Date getDefaultFromDate(){        
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();  
        calendar.add(Calendar.DAY_OF_MONTH, -(Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime()))-1));
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sf.parse(sf.format(calendar.getTime()));
        } catch (ParseException ex) {
            logger.error(ex);
        }
        return null;
    }
    
    private Date getDefaultToDate(){        
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();                
        return calendar.getTime();
    }    
}
