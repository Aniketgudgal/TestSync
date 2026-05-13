package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.TestSync.Model.AdminModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

/**
 * Servlet implementation class ViewAdminData
 */
@WebServlet("/viewadmin")
public class ViewAdminData extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter(); 
		
		RequestDispatcher r= request.getRequestDispatcher("AdminDashboard.html");
		r.include(request, response);
		out.println("<div class='table-responsive'>");

		out.println("<table class='table table-bordered table-hover table-dark text-center align-middle'>");

		// Table Header
		out.println("<thead class='table-secondary text-dark'>");
		out.println("<tr>"); 
		out.println("<th>ADMIN NAME</th>");
		out.println("<th>EMAIL</th>");
		out.println("<th>PASSOWORD</th>");
		out.println("<th>UPDATE</th>"); 
		out.println("</tr>");
		out.println("</thead>");

		// Table Body
		out.println("<tbody>");
		 
		 
		out.println("<tr>");
		out.println("<td colspan='4' class='text-danger'>No Record Found</td>");
		out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
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
