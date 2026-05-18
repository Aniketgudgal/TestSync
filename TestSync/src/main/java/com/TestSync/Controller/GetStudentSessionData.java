package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/getstudentsession")
public class GetStudentSessionData extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("studentName") != null)
		{
			String studentName = (String)session.getAttribute("studentName");
			response.getWriter().write(studentName);	 
		}
		else {
			response.getWriter().write("Not Logged In");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
