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
package net.java.javamoney.ri.convert.provider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.money.CurrencyUnit;
import javax.money.convert.ConversionProvider;
import javax.money.convert.CurrencyConverter;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ExchangeRateType;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * A Currency conversion provider for MtGox service.
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class MtGoxV2ConversionProvider implements ConversionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MtGoxV2ConversionProvider.class);
    private static final ExchangeRateType RATE_TYPE = ExchangeRateType.of("MtGox");
    private static final String PROVIDER_URL = "http://data.mtgox.com/api/2/BTC";
    private static final String BASE_CURRENCY_CODE = "BTC";

    /**
     * Contains MtGox supported currency types.
     */
    public static enum MtGoxCurrency {

        USD("USD"), AUD("AUD"), CAD("CAD"), CHF("CHF"), CNY("CNY"), DKK("DKK"), EUR("EUR"), GBP("GBP"), HKD("HKD"), JPY("JPY"), NZD("NZD"), PLN("PLN"), RUB("RUB"), SEK("SEK"), SGD("SGD"), THB("THB"), NOK("NOK"), CZK("CZK");
        private final String currency;
        private ArrayList<String> currencies;

        public String currency() {
            return currency;
        }

        private MtGoxCurrency(String currency) {
            this.currency = currency;

        }

        public static boolean isSupported(String newCurrency) {
            try {
                valueOf(newCurrency);
            } catch (IllegalArgumentException e) {
                return false;
            }
            return true;

        }
    }
    private CurrencyConverter currencyConverter = new DefaultCurrencyConverter(this);

    public MtGoxV2ConversionProvider() {
    }

    /*
     * TODO: In progress. 
     */
    public static void lookUpRate(String curCode) throws IOException {

        ObjectMapper m = new ObjectMapper();
         System.getProperties().put("http.proxyHost", "proxy.logica.com");
        System.getProperties().put("http.proxyPort", "80");
        JsonNode root = m.readTree(new URL(PROVIDER_URL + curCode + "/money/ticker").openStream());
        LOGGER.info("Result : " +root.path("result"));
        System.out.println( "Result : " +root.path("result").getTextValue());
        
        JsonNode lastNode = root.findValue("last");
        
        System.out.println( "display_short : " +lastNode.path("display_short").getTextValue());
        
    }

    public static void main(String... arg) throws IOException {
        System.getProperties().put("http.proxyHost", "proxy.logica.com");
        System.getProperties().put("http.proxyPort", "80");
        MtGoxV2ConversionProvider.lookUpRate("USD");
    }

    @Override
    public ExchangeRateType getExchangeRateType() {
        return RATE_TYPE;
    }

    @Override
    public boolean isAvailable(CurrencyUnit base, CurrencyUnit term) {
        return getExchangeRate(base, term, null) != null;
    }

    @Override
    public boolean isAvailable(CurrencyUnit base, CurrencyUnit term, Long timestamp) {
        return getExchangeRate(base, term, timestamp) != null;
    }

    @Override
    public ExchangeRate getExchangeRate(CurrencyUnit base, CurrencyUnit term, Long timestamp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public ExchangeRate getExchangeRate(CurrencyUnit base, CurrencyUnit term) {
        if (!MtGoxCurrency.isSupported(base.getCurrencyCode()) || MtGoxCurrency.isSupported(term.getCurrencyCode())) {
            return null;
        }

        return new ExchangeRate.Builder().setExchangeRateType(RATE_TYPE).setBase(base).setTerm(term).build();
    }

    @Override
    public ExchangeRate getReversed(ExchangeRate rate) {
        return getExchangeRate(rate.getTerm(), rate.getBase(),
                rate.getValidFrom());
    }

    @Override
    public CurrencyConverter getConverter() {
        return currencyConverter;
    }
}
