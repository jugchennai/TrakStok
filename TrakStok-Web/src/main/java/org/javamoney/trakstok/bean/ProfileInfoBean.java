package org.javamoney.trakstok.bean;

import java.math.BigDecimal;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.agorava.empireavenue.model.BankBalance;
import org.agorava.empireavenue.model.ProfileInfo;
import org.javamoney.trakstok.model.UserInfo;
import org.javamoney.trakstok.model.money.TSAmount;
import org.javamoney.trakstok.model.money.TSNumber;
import org.javamoney.trakstok.rest.client.EmpireAvenueClient;

@ManagedBean
@SessionScoped
public class ProfileInfoBean {

	// ProfileInfo profileInfo;

	private String firstName;
	private String lastName;
	private String ticker;
	private String joined;
	private String location;
	private String country;
	private Integer totalShares;

	private TSAmount bankBalance;

	@ManagedProperty(value = "#{userInfo}")
	private UserInfo userInfo;

	EmpireAvenueClient client = new EmpireAvenueClient();

	public ProfileInfoBean() {

	}

	public void setProfileInfo(ProfileInfo profileInfo) {

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

		if (userInfo.getProfileInfo() != null) {

			setProfileInfo(userInfo.getProfileInfo());

			BankBalance bankBalance = client.getBankBalance();
			setBankBalance(bankBalance.getBalance());

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
				userInfo.getPreferredCurrency());
	}

	public String home() {
		return "profileHomeSuccess";
	}

	public String getBankBalanceWithCurrency() {

		MonetaryAmountFormat form = MonetaryFormats.getAmountFormat(Locale.getDefault());
		String amount = form.format(getBankBalance());

		return getBankBalance().getCurrency().getCurrencyCode() + " " + amount;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
