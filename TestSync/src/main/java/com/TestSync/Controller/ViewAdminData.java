package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Identity;

@WebServlet("/viewadmin")
public class ViewAdminData extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		RequestDispatcher r = request.getRequestDispatcher("AdminDashboard.html");
		r.include(request, response);

		// get admin data from session
		HttpSession session = request.getSession();
		String adminName = (String) session.getAttribute("adminName");
		System.out.println(adminName);
		String email = (String) session.getAttribute("adminEmail");
		String password = (String) session.getAttribute("password");
		int id = (int)session.getAttribute("adminId");
		System.out.println("Admin Session id: "+id);
		out.println("<div class='container mt-4'>");

		out.println("<div class='card shadow-lg'>");

		out.println("<div class='card-header bg-dark text-white text-center'>");
		out.println("<h4>Admin Profile</h4>");
		out.println("</div>");

		out.println("<div class='card-body p-4'>");

		 
		out.println("<form action='updateadminprofile' method='GET'>");

		// ADMIN NAME
		out.println("<div class='mb-3'>");
		out.println("<label class='form-label'>Admin Name</label>");
		out.println("<input type='text' name='adminName' class='form-control' value='" + adminName + "'  >");
		out.println("</div>");

		// EMAIL
		out.println("<div class='mb-3'>");
		out.println("<label class='form-label'>Email</label>");
		out.println("<input type='email' name='email' class='form-control' value='" + email + "'  >");
		out.println("</div>");

		// PASSWORD
		out.println("<div class='mb-3'>");
		out.println("<label class='form-label'>Password</label>");
		out.println("<input type='text' name='password' class='form-control' value='" + password + "'  >");
		out.println("<input type='hidden' name='admin_Id' class='form-control' value='" + id + "'  >");
		out.println("</div>");

		// UPDATE BUTTON
		out.println("<div class='text-center'>");
		out.println("<button type = 'submit' class='btn btn-warning btn-sm px-4 py-2'>Update Profile</button>");
		out.println("</div>");

		// FORM END
		out.println("</form>");

		out.println("</div>"); // card-body
		out.println("</div>"); // card
		out.println("</div>"); // container
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}