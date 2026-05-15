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

		// Container Start
		out.println("<div class='container'>");
		// Table Start
		out.println("<table class='table table-hover text-center'>");

		// Table Header
		out.println("<thead class='table-primary'>");
		out.println("<tr>");
		out.println("<th>SR NO</th>");
		out.println("<th>COURSE NAME</th>");
		out.println("<th>UPDATE</th>");
		out.println("<th>DELETE</th>");
		out.println("</tr>");
		out.println("</thead>");

		// Table Body
		out.println("<tbody>");

		CourseService courseService = new CourseServiceImpl();
		Optional<List<CourseModel>> o = courseService.getAllCourses();

		if (o.isPresent()) {

			List<CourseModel> list = o.get();
			int count = 0;

			if (list.isEmpty()) {

				out.println("<tr>");
				out.println("<td colspan='4'>No Record Found</td>");
				out.println("</tr>");

			} else {

				for (CourseModel model : list) {

					++count;

					out.println("<tr>");

					out.println("<td>" + count + "</td>");
					out.println("<td>" + model.getCourseName() + "</td>");

					// Update Button
					out.println("<td>");
					out.println("<a name='update' href='#' class='btn btn-warning btn-sm'>");
					out.println("<i class='bi bi-pencil-square'></i>");
					out.println("</a>");
					out.println("</td>");

					// Delete Button
					out.println("<td>");
					out.println("<a name='delete' href='#' class='btn btn-danger btn-sm'>");
					out.println("<i class='bi bi-trash'></i>");
					out.println("</a>");
					out.println("</td>");

					out.println("</tr>");
				}
			}

		} else {

			out.println("<tr>");
			out.println("<td colspan='4'>Something Went Wrong</td>");
			out.println("</tr>");
		}

		out.println("</tbody>");
		out.println("</table>");

		// Container End
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}