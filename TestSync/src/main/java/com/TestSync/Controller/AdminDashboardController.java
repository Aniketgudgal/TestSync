package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet("/admindashboard")
public class AdminDashboardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("adminId") == null)
		{
			response.sendRedirect("AdminLogin.html");
			return;
		}
		request.getRequestDispatcher("AdminDashboard.html").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
