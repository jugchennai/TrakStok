package org.javamoney.trakstok.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.javamoney.trakstok.model.Currency;
import org.javamoney.trakstok.utils.CurrencyType;

@Path("exchange")
@Produces("application/json")
public class TSExchangeRateService {

    @GET
    @Path("/supportedcurrencies")
    public List<Currency> supportedCurrencies() {
        ArrayList<Currency> currencies = new ArrayList<>();
        for (CurrencyType type : CurrencyType.values())
            currencies.add(new Currency(type.code(), type.currencyName()));
        return currencies;
    }

    @GET
    @Path("/convert/{fromCurrency}/to/{toCurrency}/value/{amount}")
    public void getExchangeRate(@PathParam("fromCurrency")
    String fromCurrency, @PathParam("toCurrency")
    String toCurrency, @PathParam("amount")
    float amount) {
        System.out.println(fromCurrency + " " + toCurrency + " " + amount);
    }

}
