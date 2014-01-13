package org.javamoney.trakstok.utils;

public enum CurrencyType {

    USD("USD", "US Dollar"), AUD("AUD", "Australian Dollar"), CAD("CAD", "Canadian Dollar"), CHF("CHF", "Swiss Franc"),
    CNY("CNY", "Chinese Yuan"), DKK("DKK", "Danish Krone"),
    EUR("EUR", "Euro"), GBP("GBP", "Great British Pound"), HKD("HKD", "Hong Kong Dollar"), JPY("JPY", "Japanese Yen"), NZD("NZD", "New Zealand Dollar"),
    PLN("PLN", "Polish Złoty"), RUB("RUB", "Russian Rouble"), SEK("SEK", "Swedish Krona"), SGD("SGD", "Singapore Dollar"), THB("THB", "  Thai Baht"), NOK("NOK", "    Norwegian Krone"), CZK("CZK",
            "Czech Koruna"),
    BTC("BTC", "BitCoin"), EVS("EVS", "EmpireAvene Eave");

    private final String currencyCode;
    private final String currencyName;

    public String code() {
        return currencyCode;
    }

    public String currencyName() {
        return currencyName;
    }

    private CurrencyType(String code, String currencyName) {
        this.currencyCode = code;
        this.currencyName = currencyName;
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
