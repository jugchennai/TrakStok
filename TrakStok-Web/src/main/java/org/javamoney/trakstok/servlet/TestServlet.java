package org.javamoney.trakstok.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agorava.empireavenue.model.BankBalance;
import org.agorava.empireavenue.model.ProfileInfo;
import org.javamoney.trakstok.bean.ProfileInfoBean;
import org.javamoney.trakstok.model.Currency;
import org.javamoney.trakstok.model.UserInfo;
import org.javamoney.trakstok.rest.client.EmpireAvenueClient;

@WebServlet(value = "/test", name = "TestServlet")
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		EmpireAvenueClient client = new EmpireAvenueClient();
		ProfileInfo profileInfo = client.getProfileInfo();
		BankBalance bankBalance = client.getBankBalance();
				
		System.out.println("Profile " + profileInfo.toString());

		UserInfo userInfo = (UserInfo) req.getSession().getAttribute("userInfo");
		
		if(userInfo == null) {
			System.out.println("userInfo is null");
			userInfo = new UserInfo();
		}
		userInfo.setLoggedIn(true);
		userInfo.setUserName(profileInfo.getFirstName());
		userInfo.setDisplayName(profileInfo.getFullName());
		userInfo.setPreferredCurrency(new Currency("EVS", "EmpireAvene Eave"));
		
		req.getSession().setAttribute("userInfo", userInfo);

		
		ProfileInfoBean profileInfoBean = new ProfileInfoBean(profileInfo);
		profileInfoBean.setBankBalance(bankBalance.getBalance());
		req.getSession().setAttribute("profileInfoBean", profileInfoBean);

		req.getRequestDispatcher("/icallback.xhtml").forward(req, resp);
		
	}

}
