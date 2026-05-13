package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class GetAdminSessionData
 */
@WebServlet("/getadminsessiondata")
public class GetAdminSessionData extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("adminName") != null) {

			String adminName = (String) session.getAttribute("adminName");
			response.getWriter().write(adminName);
		}
		else {
			response.getWriter().write("Not Logged In");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
