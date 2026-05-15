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

import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/viewExam")
public class ViewExam extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class = 'container'>");
		out.println("<table class = 'table table-hover text-center'>");
		out.println("<thead class = 'table-primary'>");
		out.println("<tr>");
		out.println("<th>Sr No</th>");
		out.println("<th>Exam Name</th>");
		out.println("<th>Subject</th>");
		out.println("<th>Total Questions</th>");
		out.println("<th>Total Marks</th>");
		out.println("<th>Duration</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody class = ''>");
		AdminService as = new AdminServiceImpl();

		Optional<List<Object[]>> o = as.getExamWithSubject();
		if (o.isPresent()) {
			List<Object[]> list = o.get();
			if (!list.isEmpty()) {
				int count = 1;
				for (Object[] obj : list) {
					out.println("<tr>");
					out.println("<td>" + (count++) + "</td>");
					out.println("<td>" + obj[0] + "</td>");
					out.println("<td>" + obj[1] + "</td>");
					out.println("<td>" + obj[2] + "</td>");
					out.println("<td>" + obj[3] + "</td>");
					out.println("<td>" + obj[4] + "</td>");
					out.println("</tr>");
				}
			} else {
				out.println("<tr> <td colspan = '6'>No Data Found</td></tr>");
			}
		} else {
			out.println("<tr> <td colspan = '6'>No Data Found</td></tr>");
		}

		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
