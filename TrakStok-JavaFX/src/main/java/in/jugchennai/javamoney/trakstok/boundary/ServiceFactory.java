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
package in.jugchennai.javamoney.trakstok.boundary;

import in.jugchennai.javamoney.convert.CurrencyConverter;
import in.jugchennai.javamoney.convert.CurrencyConverterImpl;
import in.jugchennai.javamoney.jpa.service.UserService;
import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsCurrency;
import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.concurrent.Task;
import javax.money.Money;
import javax.money.MoneyCurrency;

/**
 *
 * @author gshenoy
 */
public class ServiceFactory {

    public static final String SOURCE_CURRENCY_CODE = "USD";
    static ServiceFactory instance = null;
    TsUsers loggedInUser;
    TsCompany company;

    public static ServiceFactory get() {

        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public TsUsers getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(TsUsers loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public TsCompany getCompany() {
        return company;
    }

    public void setCompany(TsCompany company) {
        this.company = company;
    }

    private ServiceFactory() {
    }

    public AsyncService<TsUsers> loginService(String userName, String password) {
        return new LoginService(userName, password);
    }

    class LoginService extends AsyncService<TsUsers> {

        String userName;
        String password;

        public LoginService(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        @Override
        protected Task<TsUsers> createTask() {

            return new Task<TsUsers>() {
                @Override
                protected TsUsers call() throws Exception {
                    Collection<TsUsers> findByUsername = UserService.findByUsername(userName);
                    for (TsUsers next : findByUsername) {
                        if (next.getPassword().equals(password)) {
                            
                            System.out.println("Logged in");
                            return next;

                        }
                    }
                    return null;
                }
            };

        }
    }

    public AsyncService<Collection<TsCompany>> companyService() {
        return new CompanyService();
    }

    class CompanyService extends AsyncService<Collection<TsCompany>> {

        @Override
        protected Task<Collection<TsCompany>> createTask() {

            return new Task<Collection<TsCompany>>() {
                @Override
                protected Collection<TsCompany> call() throws Exception {

                    return (Collection<TsCompany>) in.jugchennai.javamoney.jpa.service.CompanyService.findAllCompanies();
                }
            };

        }
    }

    public AsyncService<Collection<TsCurrency>> currencyService() {
        return new CurrencyService();
    }

    class CurrencyService extends AsyncService<Collection<TsCurrency>> {

        @Override
        protected Task<Collection<TsCurrency>> createTask() {

            return new Task<Collection<TsCurrency>>() {
                @Override
                protected Collection<TsCurrency> call() throws Exception {

                    return (Collection<TsCurrency>) in.jugchennai.javamoney.jpa.service.CompanyService.getCurrencyList("%");
                }
            };

        }
    }

    public AsyncService<Map<Object, Number>> trendService(String symbol, TrendFrequency frequency, String targetCurrencyCode) {
        return new TrendService(symbol, frequency, targetCurrencyCode);
    }

    class TrendService extends AsyncService<Map<Object, Number>> {

        String symbol;
        TrendFrequency frequency;
        String targetCurrencyCode;

        public TrendService(String symbol, TrendFrequency frequency, String targetCurrencyCode) {
            this.symbol = symbol;
            this.frequency = frequency;
            this.targetCurrencyCode = targetCurrencyCode;
        }

        @Override
        protected Task<Map<Object, Number>> createTask() {
            return new Task<Map<Object, Number>>() {
                @Override
                protected Map<Object, Number> call() throws Exception {

                    return getTrendMap(symbol, frequency, targetCurrencyCode);
                }
            };
        }
    }

    public AsyncService<Map<String, Map<Object, Number>>> compareTrendService(String symbol1, String symbol2, TrendFrequency frequency, String targetCurrencyCode) {
        return new CompareTrendService(symbol1, symbol2, frequency, targetCurrencyCode);
    }

    class CompareTrendService extends AsyncService<Map<String, Map<Object, Number>>> {

        String symbol1;
        String symbol2;
        TrendFrequency frequency;
        String targetCurrencyCode;

        public CompareTrendService(String symbol1, String symbol2, TrendFrequency frequency, String targetCurrencyCode) {
            this.symbol1 = symbol1;
            this.symbol2 = symbol2;
            this.frequency = frequency;
            this.targetCurrencyCode = targetCurrencyCode;
        }

        @Override
        protected Task<Map<String, Map<Object, Number>>> createTask() {
            return new Task<Map<String, Map<Object, Number>>>() {
                @Override
                protected Map<String, Map<Object, Number>> call() throws Exception {

                    Map<String, Map<Object, Number>> compareTrendMap = new HashMap<String, Map<Object, Number>>();

                    LinkedHashMap<Object, Number> result1 = getTrendMap(symbol1, frequency, targetCurrencyCode);
                    LinkedHashMap<Object, Number> result2 = getTrendMap(symbol2, frequency, targetCurrencyCode);

                    compareTrendMap.put(symbol1, result1);
                    compareTrendMap.put(symbol2, result2);

                    return compareTrendMap;
                }
            };
        }
    }

    private LinkedHashMap<Object, Number> getTrendMap(String symbol, TrendFrequency frequency, String targetCurrencyCode) {

        LinkedHashMap<Object, Number> result = in.jugchennai.javamoney.jpa.service.CompanyService.getTrendMap(symbol, frequency, getDefaultFromDate(), getDefaultToDate());

        if (!targetCurrencyCode.equals(SOURCE_CURRENCY_CODE)) {
            CurrencyConverter converter = new CurrencyConverterImpl(MoneyCurrency.of(SOURCE_CURRENCY_CODE), MoneyCurrency.of(targetCurrencyCode));
            LinkedHashMap<Object, Number> returnResult = new LinkedHashMap<>();
            for (Object key : result.keySet()) {
                returnResult.put(key, (converter.convert(Money.of(SOURCE_CURRENCY_CODE, result.get(key)))).doubleValue());
            }

            return returnResult;
        }
        return result;

    }

    private Date getDefaultFromDate() {

        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -(Integer.parseInt(new SimpleDateFormat("dd").format(calendar.getTime())) - 1));
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sf.parse(sf.format(calendar.getTime()));
        } catch (ParseException ex) {
        }
        return null;
    }

    private Date getDefaultToDate() {

        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        return calendar.getTime();
    }
}
