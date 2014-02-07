package org.javamoney.trakstok.bean;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.money.MonetaryAmount;

import org.agorava.empireavenue.model.ProfileInfo;
import org.javamoney.trakstok.model.Currency;
import org.javamoney.trakstok.model.money.TSAmount;
import org.javamoney.trakstok.model.money.TSNumber;

@ManagedBean
@SessionScoped
public class ProfileInfoBean {

	ProfileInfo profileInfo;

	private String firstName;
	private String lastName;
	private String ticker;
	private String joined;
	private String location;
	private String country;
	private Integer totalShares;

	private TSAmount bankBalance;

	public ProfileInfoBean() {

	}

	public ProfileInfoBean(ProfileInfo profileInfo) {
		this.profileInfo = profileInfo;

		this.firstName = profileInfo.getFirstName();
		this.lastName = profileInfo.getLastName();
		this.ticker = profileInfo.getTicker();
		this.joined = profileInfo.getJoined();
		this.location = profileInfo.getLocation();
		this.country = profileInfo.getCountry();
		this.totalShares = profileInfo.getTotalShares();

	}

	public String getTicker() {
		return ticker;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getJoined() {
		return joined;
	}

	public String getCountry() {
		return country;
	}

	public String getTotalShares() {
		return String.valueOf(totalShares);
	}

	public String getLocation() {
		return location;
	}

	public String getProfileInfo() {

		if (profileInfo != null) {
			return "profileSuccess";
		} else {
			return "failure";
		}

	}

	public TSAmount getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(BigDecimal bankBalance) {
		this.bankBalance = new TSAmount(new TSNumber(bankBalance),
				new Currency("EVS", "EmpireAvene Eave"));
	}

	public String home() {
		return "profileHomeSuccess";
	}

	public String getBankBalanceWithCurrency() {
		return getBankBalance().getCurrency().getCurrencyCode() + " "
				+ String.valueOf(getBankBalance().getNumber().longValue());
	}

}
