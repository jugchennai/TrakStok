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
package in.jugchennai.javamoney.trakstok.service;

import in.jugchennai.javamoney.convert.CurrencyConverter;
import in.jugchennai.javamoney.convert.CurrencyConverterImpl;
import in.jugchennai.javamoney.jpa.service.CompanyService;
import in.jugchennai.javamoney.jpa.service.UserService;
import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsCurrency;
import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import in.jugchennai.javamoney.trakstok.beans.Company;
import in.jugchennai.javamoney.trakstok.beans.Currency;
import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.money.Money;
import javax.money.MoneyCurrency;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokService</strong>.
 *
 * @author
 */
public final class TrakStokService extends ServiceBase {

    public static final String SOURCE_CURRENCY_CODE = "USD";
    
    public static final WaveTypeBase DO_LOGIN = WaveTypeBase.build("LOGIN", TSWaves.USER);
    public static final WaveTypeBase RE_ON_LOGIN = WaveTypeBase.build("ON_LOGIN", TSWaves.USER);
    public static final WaveTypeBase DO_FIND_COMPANIES = WaveTypeBase.build("FIND_ALL_COMPANIES");
    public static final WaveTypeBase RE_ON_FIND_COMPANIES = WaveTypeBase.build("ON_FIND_COMPANIES", TSWaves.COMPANIES);
    public static final WaveTypeBase DO_FIND_CURRENCIES = WaveTypeBase.build("FIND_ALL_CURRENCIES");
    public static final WaveTypeBase RE_ON_FIND_CURRENCIES = WaveTypeBase.build("ON_FIND_CURRENCIES", TSWaves.CURRENCIES);
    public static final WaveTypeBase DO_FIND_TREND = WaveTypeBase.build("FIND_TREND");
    public static final WaveTypeBase RE_ON_FIND_TREND = WaveTypeBase.build("ON_FIND_TREND", TSWaves.TREND);
    public static final WaveTypeBase DO_COMPARE_TREND = WaveTypeBase.build("COMPARE_TREND");
    public static final WaveTypeBase RE_ON_COMPARE_TREND = WaveTypeBase.build("ON_COMPARE_TREND", TSWaves.COMPARE_TREND);
    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        LOGGER.info("TrakStokService.ready()");

        // Define the service method
        registerCallback(DO_LOGIN, RE_ON_LOGIN);



    }

    /**
     * Do something.
     *
     * @param wave the source wave
     */
    public User doLogin(final User user, final Wave wave) {

        LOGGER.info("doLogin()");

        Collection<TsUsers> users = UserService.findByUsername(user.getName());

        for (TsUsers tsUser : users) {

            if (tsUser.getPassword().equals(user.getPassword())) {

                user.setLastLogin(tsUser.getLastlogin().toString());
                user.setDisplayName(tsUser.getDisplayname());

            }
        }

        return null;

    }

    public List<Company> doFindAllCompanies(final Wave wave) {

        Collection<TsCompany> companies = CompanyService.findAllCompanies();

        List<Company> allCompanies = new ArrayList<Company>();
        for (TsCompany company : companies) {

            Company cy = new Company(company.getDisplayname());
            allCompanies.add(cy);
        }

        return allCompanies;
    }

    public List<Currency> doFindAllCurrencies(final Wave wave) {

        Collection<TsCurrency> companies = CompanyService.getCurrencyList("%");

        List<Currency> allCurrencies = new ArrayList<Currency>();
        for (TsCurrency currency : companies) {

            Currency cy = new Currency();
            cy.setName(currency.getCurrencyName());
            allCurrencies.add(cy);
        }

        return allCurrencies;

    }

    public Map<Object, Number> doFindTrend(final String symbol, final TrendFrequency frequency, final String targetCurrencyCode, final Wave wave) {
        return getTrendMap(symbol, frequency, targetCurrencyCode);
    }

    public Map<String, Map<Object, Number>> doCompareTrend(final String symbol1, final String symbol2, final TrendFrequency frequency, final String targetCurrencyCode, final Wave wave) {

        Map<String, Map<Object, Number>> compareTrendMap = new HashMap<String, Map<Object, Number>>();

        LinkedHashMap<Object, Number> result1 = getTrendMap(symbol1, frequency, targetCurrencyCode);
        LinkedHashMap<Object, Number> result2 = getTrendMap(symbol2, frequency, targetCurrencyCode);

        compareTrendMap.put(symbol1, result1);
        compareTrendMap.put(symbol2, result2);

        return compareTrendMap;

    }

    private LinkedHashMap<Object, Number> getTrendMap(String symbol, TrendFrequency frequency, String targetCurrencyCode) {

        LinkedHashMap<Object, Number> result = CompanyService.getTrendMap(symbol, frequency, getDefaultFromDate(), getDefaultToDate());

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
