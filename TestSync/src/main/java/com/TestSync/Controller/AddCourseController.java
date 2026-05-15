package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddCourseController
 */
@WebServlet("/addcourse")
public class AddCourseController extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.html");
		rd.include(request, response);
		
		out.println("<div class='container col-md-6 mt-5 p-3'>");
		out.println("<h2>Add Course</h2>");
		out.println("<input type='text' class='col-md-6' value='' name='courseName'>");
		out.println("<Button type='Submit' class='col-md-6'>Add Course</Button>");
		out.println("</div>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
