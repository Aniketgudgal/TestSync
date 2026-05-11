package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.TestSync.Model.AdminModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/validateadmin")
public class AdminController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		 
		
		
		AdminModel model = new AdminModel();
		model.setEmail(email);
		model.setPassword(password);
		AdminService adminService = new AdminServiceImpl();
		Boolean result = adminService.validateAdmin(model); 

		if (result) {
			out.println(
					"<html><body><script>" + "alert('Admin Logging Successfully....!');</script></body></html>");
		} else {

			out.println("<html><body><script>"
					+ "alert('Something Went Wrong....?'); window.location = 'AdminLogin.html'</script></body></html>");
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
