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
import java.util.Optional;

import com.TestSync.Model.StudentModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

/**
 * Servlet implementation class ManageStudent
 */
@WebServlet("/managestudent")
public class ManageStudent extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		RequestDispatcher r = request.getRequestDispatcher("AdminDashboard.html");
		r.include(request, response);
		out.println("<div class='container mt-4'>");

		out.println("<div class='card shadow-lg'>");
		out.println("<div class='card-header bg-dark text-white text-center'>");
		out.println("<h4>Manage Student</h4>");
		out.println("</div>");

		out.println("<div class='card-body p-0'>");

		out.println("<div class='table-responsive'>");
		out.println("<table class='table table-bordered table-hover text-center mb-0'>");

		out.println("<thead class='table-dark'>");
		out.println("<tr>");
		out.println("<th>SR NO</th>");
		out.println("<th>STUDENT NAME</th>");
		out.println("<th>EMAIL</th>");
		out.println("<th>USERNAME</th>");
		out.println("<th>COURSE</th>");
		out.println("<th>MOBILE NO</th>");
		out.println("<th>UPDATE</th>");
		out.println("</tr>");
		out.println("</thead>");

		out.println("<tbody>");
		out.println("<tr>");

		AdminService adminService = new AdminServiceImpl();
		Optional<List<Object[]>> list = adminService.getAllStudents();
		int count = 0;

		if (list.isPresent() && !list.get().isEmpty()) {

			for (Object obj[] : list.get()) {
				System.out.println(obj);
				count++;

				out.println("<tr>");

				out.println("<td>" + count++ + "</td>");
				out.println("<td>" + obj[0] + "</td>");
				out.println("<td>" + obj[1] + "</td>");
				out.println("<td>" + obj[2] + "</td>");
				out.println("<td>" + obj[3] + "</td>");
				out.println("<td>" + obj[4] + "</td>");

				out.println("<td>");
				out.println("<a href='#' class='btn btn-warning btn-sm'>");
				out.println("Edit");
				out.println("</a>");
				out.println("</td>");

				out.println("</tr>");
			}
		} else {

			out.println("<tr>");
			out.println("<td colspan='8' class='text-danger'>No Record Found</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
