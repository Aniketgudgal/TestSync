package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addcourse")
public class AddCourse extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.html");
		rd.include(request, response);

		 
		out.println("<div class='container mt-5'>");

		out.println("<h2 class='text-center mb-4'>Add Course</h2>");

		out.println("<div class='row justify-content-center'>");
		out.println("<div class='col-md-6'>");

		out.println("<form action='AddCourse' method='post'>");
 
		out.println("<div class='mb-3'>");
		out.println("<label class='form-label fw-bold'>Course Name</label>");
		out.println("<input type='text' class='form-control'placeholder='Enter Course Name' name='courseName' required>");
		out.println("</div>");

		 
		out.println("<div class='text-center'>");
		out.println("<button type='submit' class='btn btn-primary px-4'>");
		out.println("Add Course");
		out.println("</button>");
		out.println("</div>");
 
		out.println("</form>");

		out.println("</div>");
		out.println("</div>");
 
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}