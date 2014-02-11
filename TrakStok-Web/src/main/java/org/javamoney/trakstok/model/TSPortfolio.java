package org.javamoney.trakstok.model;

import java.math.BigDecimal;

import org.agorava.empireavenue.model.Portfolio;
import org.javamoney.trakstok.model.money.TSAmount;
import org.javamoney.trakstok.model.money.TSNumber;

public class TSPortfolio extends Portfolio {

	TSAmount yesterdayEarningsAmt;

	public TSPortfolio() {

	}

	public void setYesterdayEarnings(float yesterdayEarnings) {

		yesterdayEarningsAmt = new TSAmount(new TSNumber(new BigDecimal(
				yesterdayEarnings)));

		super.setYesterdayEarnings(yesterdayEarnings);
	}

	public String getYesterdayEarningsAmt() {

		return String.valueOf(yesterdayEarningsAmt.getNumber().doubleValue());
	}

}
