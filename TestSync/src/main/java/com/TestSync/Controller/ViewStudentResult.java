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
import java.util.List;
import java.util.Optional;

import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/viewStudentResult")
public class ViewStudentResult extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("StudentDashboard.html");
		rst.include(request, response);
		HttpSession session = request.getSession();
		Optional<Integer> id = Optional.empty();
		try {
			id = Optional.of((int) session.getAttribute("sId"));
		} catch (Exception ex) {
			System.out.println("problem to cast session id: " + ex);
		}
		out.println("<div class = 'container'>");
		out.println("<table class = 'table text-center table-hover'>");
		out.println("<thead class = 'table-primary'>");
		out.println("<tr>");
		out.println("<td>Sr No</td>");
		out.println("<td>Subject Name</td>");
		out.println("<td>Exam Name</td>");
		out.println("<td>Date</td>");
		out.println("<td>Total Question</td>");
		out.println("<td>Total Marks</td>");
		out.println("<td>Obtain Marks</td>");
		out.println("<td>Percentage</td>");
		out.println("<td>Result</td>");
		out.println("</tr>");
		out.println("</thead>");

		out.println("<tbody>");
		Optional<Integer> sId = Optional.empty();
		try {
			sId = Optional.of((int) session.getAttribute("sId"));
		} catch (Exception ex) {
			System.out.println("Problem to insert session data: " + ex);
		}
		if (sId.isPresent()) {
			StudentService ss = new StudentServiceImp();
			Optional<List<Object[]>> o = ss.getResult(sId.get());
			if (o.isPresent()) {
				List<Object[]> list = o.get();
				int count = 1;
				if (!list.isEmpty()) {
					for (Object[] obj : list) {
						out.println("<tr>");
						out.println("<td>" + (count++) + "</td>");
						for (Object ob : obj) {
							out.println("<td>" + ob + "</td>");
						}
						out.println("</tr>");
					}
				}
				else
				{
					out.println("<tr><td colspan = '9'>No Data Present</td></tr>");
				}

			} else {
				out.println("<tr><td colspan = '9'>No Data Present</td></tr>");
			}

		} else {
			out.println("<tr><td colspan = '9'>No Data Present</td></tr>");
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
