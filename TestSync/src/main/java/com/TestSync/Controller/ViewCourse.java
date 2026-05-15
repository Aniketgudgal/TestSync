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

import com.TestSync.Model.CourseModel;
import com.TestSync.Service.CourseService;
import com.TestSync.Service.CourseServiceImpl;

@WebServlet("/viewcourse")
public class ViewCourse extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.html");
		rd.include(request, response);

		CourseService courseService = new CourseServiceImpl();
		Optional<List<CourseModel>> o = courseService.getAllCourses();

		out.println("<table class='table container p-2'>");

		out.println("<thead class='table-primary text-center'>");
		out.println("<tr>");
		out.println("<th>SR NO</th>");
		out.println("<th>COURSE NAME</th>");
		out.println("<th>UPDATE</th>");
		out.println("</tr>");
		out.println("</thead>");

		out.println("<tbody class='table-hover text-center'>");

		if (o.isPresent()) {

			List<CourseModel> list = o.get();

			if (!list.isEmpty()) {

				int count = 1;

				for (CourseModel model : list) {

					out.println("<tr>");

					out.println("<td>" + (count++) + "</td>");
					out.println("<td>" + model.getCourseName() + "</td>");

					out.println("<td>");
					out.println("<a href='#'>");
					out.println("<img src='Images/icons-update.png' " + "alt='not found' width='30'>");
					out.println("</a>");
					out.println("</td>");

					out.println("</tr>");
				}

			} else {

				out.println("<tr>");
				out.println("<td colspan='4'>No Record Found</td>");
				out.println("</tr>");
			}

		} else {

			out.println("<tr>");
			out.println("<td colspan='4'>Something Went Wrong</td>");
			out.println("</tr>");
		}

		out.println("</tbody>");
		out.println("</table>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}