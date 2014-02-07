package org.javamoney.trakstok.model;

import javax.money.CurrencyUnit;

public class Currency implements CurrencyUnit {

	private String currencyCode;
	private String currencyName;
	private int numericCode = 0;
	private int defaultFractionDigits = 0;
	
	public Currency() {
	}

	public Currency(String currencyCode, String currencyName) {
		super();
		this.currencyCode = currencyCode;
		this.currencyName = currencyName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Override
	public int getNumericCode() {
		return numericCode;
	}

	@Override
	public int getDefaultFractionDigits() {
		return defaultFractionDigits;
	}

}
