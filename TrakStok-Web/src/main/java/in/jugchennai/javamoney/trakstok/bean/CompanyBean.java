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

import in.jugchennai.javamoney.jpa.service.CompanyService;
import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import javax.faces.bean.ManagedBean;
import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Balaji T <balaji.t123@gmail.com>
 */
@ManagedBean
public class CompanyBean extends TSBaseFormBean {    
    
    private Integer companyid;
    private String displayname;
    private String symbol;

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
        String symbol = "GOOG";
        if(getSymbol() != null){
               symbol = getSymbol();          
        }        
        ChartSeries scrip = new ChartSeries(symbol);
        scrip.setData(getTrendMap(symbol, TrendFrequency.DAILY,getDefaultFromDate(),getDefaultToDate()));        
        trendModel.addSeries(scrip);
                
        return trendModel;
    }

    private LinkedHashMap<Object, Number> getTrendMap(String symbol, TrendFrequency frequency, Date fromDate, Date toDate) {    
        return CompanyService.getTrendMap(symbol,frequency,fromDate,toDate);                
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