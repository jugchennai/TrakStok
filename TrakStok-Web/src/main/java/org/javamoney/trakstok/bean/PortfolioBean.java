package org.javamoney.trakstok.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.javamoney.trakstok.model.TSPortfolio;
import org.javamoney.trakstok.model.UserInfo;
import org.javamoney.trakstok.rest.client.EmpireAvenueClient;

@ManagedBean
@SessionScoped
public class PortfolioBean {

	private List<TSPortfolio> portfolios;

	EmpireAvenueClient client = new EmpireAvenueClient();

	@ManagedProperty(value = "#{userInfo}")
	private UserInfo userInfo;

	public PortfolioBean() {

	}

	public String getPortfolio() {

		portfolios = client.getPortfolioAll();

		if (portfolios != null) {

			return "portfolioSuccess";
		} else {
			return "failure";
		}

	}

	public String home() {
		return "portfolioHomeSuccess";
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<TSPortfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(List<TSPortfolio> portfolios) {
		this.portfolios = portfolios;
	}

}
