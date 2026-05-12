package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addSubj")
public class AddSubjects extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class='container mt-3'>");
		out.println("<form class='form-group' action= 'AddSubject' method = 'GET'>");
		// first row
		out.println("<div class = 'row'>");
		out.print("<div class = 'col-3'> <input class = 'w-100 form-control' type = 'text' name = 'subjectName' value = '' placeholder = 'Enter Subject Name'></div>");
		out.println("<div class = 'col-9'><button type = 'submit' class = 'btn btn-success'>Add Subject</button></div>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
