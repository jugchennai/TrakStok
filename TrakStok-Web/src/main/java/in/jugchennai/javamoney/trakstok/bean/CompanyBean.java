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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
    private Money latestShareValue;
    private double valueDiff;
    private String diffImage;
    private String anotherCompanySymbol;
    private String targetCurrency;
    private double minY;
    private double maxY;
    private static final String SOURCE_CURRENCY_CODE = "USD";
    private List<TsCurrency> currencyList = new ArrayList<TsCurrency>();

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
        if(displayname == null){
            populateCompanyDetails();
        }
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

    public Money getLatestShareValue() {
        return latestShareValue;
    }

    public void setLatestShareValue(Money latestShareValue) {
        this.latestShareValue = latestShareValue;
    }

    public double getValueDiff() {
        return valueDiff;
    }

    public void setValueDiff(double valueDiff) {
        this.valueDiff = valueDiff;
    }

    public String getDiffImage() {
        return diffImage;
    }

    public void setDiffImage(String diffImage) {
        this.diffImage = diffImage;
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

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
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
        String _symbol;
        if(getSymbol() != null && !getSymbol().equals("")){
               _symbol = getSymbol();          
        }else{
            _symbol = getDefaultCompany().getSymbol();
        }
        String targetCurrencyCode = SOURCE_CURRENCY_CODE;
        if(getTargetCurrency() != null && !getTargetCurrency().equals("")){
            targetCurrencyCode = getTargetCurrency();
        }
        ChartSeries scrip = new ChartSeries(_symbol+" in "+targetCurrencyCode);
        scrip.setData(getTrendMap(_symbol, TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate(),targetCurrencyCode,false));        
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
        String _symbol;
        _symbol = getNotNullSymbol();        
        String targetCurrencyCode = SOURCE_CURRENCY_CODE;
        if(getTargetCurrency() != null && !getTargetCurrency().equals("")){
            targetCurrencyCode = getTargetCurrency();
        }
        ChartSeries scrip = new ChartSeries(_symbol+" in "+ targetCurrencyCode);
        scrip.setData(getTrendMap(_symbol, TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate(), targetCurrencyCode, false));        
        trendModel.addSeries(scrip);
        
        logger.debug("Another symbol "+getAnotherCompanySymbol());        
        if(getAnotherCompanySymbol() != null && !getAnotherCompanySymbol().equals("")){
            ChartSeries anotherScrip = new ChartSeries(getAnotherCompanySymbol()+" in "+targetCurrencyCode);
            anotherScrip.setData(getTrendMap(getAnotherCompanySymbol(), TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate(), targetCurrencyCode, true));        
            trendModel.addSeries(anotherScrip);
        }
        
        logger.debug(trendModel.getSeries());
        return trendModel;
    }

    public List<TsCurrency> getCurrencyList() {
        currencyList = CompanyService.getCurrencyList();
        return currencyList;
    }

    public void setCurrencyList(List<TsCurrency> currencyList) {
        this.currencyList = currencyList;
    }
    
//    public List<TsCurrency> getCurrencyList(){
//        currencyList = CompanyService.getCurrencyList();
//        return currencyList;
//    }
    
    public List<TsCurrency> getCurrencyList(String currencyCode){
        logger.info("currency code is "+currencyCode);
        return CompanyService.getCurrencyList(currencyCode);
    }
       
    private void populateCompanyDetails(){        
        TsCompany tsCompany;
        String _symbol = getNotNullSymbol();
        String targetCurrencyCode = getTargetCurrency();
        tsCompany = (CompanyService.findBySymbol(_symbol)).iterator().next();
        logger.info("Populate company details "+tsCompany);
        setDisplayname(tsCompany.getDisplayname());
        if(targetCurrencyCode != null && !targetCurrencyCode.equals(SOURCE_CURRENCY_CODE)){
            CurrencyConverter converter = new CurrencyConverterImpl(MoneyCurrency.of(SOURCE_CURRENCY_CODE), MoneyCurrency.of(targetCurrency));
            setLatestShareValue(converter.convert((Money)tsCompany.getLatestShareValue()));
            setValueDiff(converter.convert(Money.of(SOURCE_CURRENCY_CODE,tsCompany.getValueDiff())).doubleValue());            
        }else{
            setLatestShareValue((Money) tsCompany.getLatestShareValue());
            setValueDiff(tsCompany.getValueDiff());
        }
        logger.info("Latest share value "+getLatestShareValue());
        logger.info("Value diff "+getValueDiff());
        setDiffImage(computeDiffImage(tsCompany.getValueDiff()));        
    }
    
    private String computeDiffImage(double value){
        String imageName = "equal";        
        if( value > 0){
            imageName = "up";
        }else if(value < 0){
            imageName = "down";
        }
        return imageName;
    }

    private LinkedHashMap<Object, Number> getTrendMap(String symbol, TrendFrequency frequency, Date fromDate, Date toDate, String targetCurrencyCode, boolean secondSymbol) {    
              
        LinkedHashMap<Object, Number> result = CompanyService.getTrendMap(symbol,frequency,fromDate,toDate);                
        if(!targetCurrencyCode.equals(SOURCE_CURRENCY_CODE)){
            CurrencyConverter converter = new CurrencyConverterImpl(MoneyCurrency.of(SOURCE_CURRENCY_CODE), MoneyCurrency.of(targetCurrencyCode));
            LinkedHashMap<Object, Number> returnResult = new LinkedHashMap<>();
        for(Object key : result.keySet()){
            returnResult.put(key, (converter.convert(Money.of(SOURCE_CURRENCY_CODE, result.get(key)))).doubleValue());
        }
            calculateBoundaries(returnResult,secondSymbol);
            return returnResult;
        }
        calculateBoundaries(result,secondSymbol);
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

    private void calculateBoundaries(LinkedHashMap<Object, Number> result, boolean secondSymbol) {
        Set<Number> valueSet = new TreeSet<>();
        for(Object key : result.keySet()){
            valueSet.add(result.get(key));
        }
        Object[] valueArray = valueSet.toArray();
        if(secondSymbol){
            double secondMinY = (double)valueArray[0];
            double secondMaxY = (double)valueArray[valueArray.length-1];
            secondMinY-=10;
            secondMaxY+=10;
            if(secondMinY < minY){
                minY = secondMinY;                
            }
            if(secondMaxY > maxY){
                maxY = secondMaxY;                
            }
        }else{
            minY = (double)valueArray[0];
            maxY = (double)valueArray[valueArray.length-1];
            minY-=10;
            maxY+=10;
        }
        
    }

    private TsCompany getDefaultCompany() {
        TsCompany defaultCompany = null;
        for (TsCompany company : (Collection<TsCompany>)CompanyService.findAllCompanies()){
            defaultCompany = company;
            if(company == null){
                continue;
            }
            break;
        }
        return defaultCompany;
    }

    private String getNotNullSymbol() {
        String _symbol;
        if(getSymbol() != null && !getSymbol().equals("")){
               _symbol = getSymbol();          
        }else{
            _symbol = getDefaultCompany().getSymbol();
        }
        return _symbol;
    }    
}
