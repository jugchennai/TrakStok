package org.javamoney.trakstok.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agorava.empireavenue.model.ProfileInfo;
import org.javamoney.trakstok.rest.client.EmpireAvenueClient;


@WebServlet(value = "/test", name = "TestServlet")
public class TestServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		EmpireAvenueClient client = new EmpireAvenueClient();
		ProfileInfo profileInfo = client.getProfileInfo();
		
		System.out.println("Profile " + profileInfo.toString());
		
	}

}
