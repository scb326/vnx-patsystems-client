package com.vnx.patsystems.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vnx.patsystems.util.Feeder;
import com.vnx.patsystems.util.Util;

public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static volatile boolean isFeederReady = false;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		if(!isFeederReady) {
			isFeederReady = true;
			new Feeder();
		}

		response.setHeader("Refresh", "1");
		HttpSession session = request.getSession();
		session.setAttribute("quoteTables", Util.getQuoteTable());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/index.jsp");
		requestDispatcher.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		doGet(request, response);
	}
}