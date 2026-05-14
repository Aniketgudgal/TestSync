package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/viewSchedulExam")
public class ViewScheduleExam extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class = 'container'>");
		out.println("<table class = 'table table-hover text-center'>");
		out.println("<thead class = 'table-primary'>");
		out.println("<tr>");
		out.println("<th>Sr No</th>");
		out.println("<th>Exam Name</th>");
		out.println("<th>Subject Name</th>");
		out.println("<th>Course Name</th>");
		out.println("<th>Start Time</th>");
		out.println("<th>End Time</th>");
		out.println("<th>Date</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");

		AdminService as = new AdminServiceImpl();
		Optional<List<Object[]>> o = as.getExamSchedule();
		if(o.isPresent())
		{
			List<Object[]> list = o.get();
			if(!list.isEmpty())
			{
				int count = 1;
				for(Object[] obj: list)
				{
					out.println("<tr>");
					out.println("<td>"+(count++)+"</td>");
					for(int i = 0; i < obj.length; i++)
					{
						if(i == 3 || i == 4)
						{
							LocalTime t = LocalTime.parse((String)obj[i]);
							DateTimeFormatter fm = DateTimeFormatter.ofPattern("hh:mm a");
							out.println("<td>"+t.format(fm)+"</td>");
						}
						else if(i == 5)
						{
							DateTimeFormatter ip = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							DateTimeFormatter op = DateTimeFormatter.ofPattern("dd-MM-yyyy");
							LocalDate dt = LocalDate.parse((String)obj[i], ip);
							out.println("<td>"+dt.format(op)+"</td>");
							
						}
						else
						{
							out.println("<td>"+obj[i]+"</td>");
						}
					}
					out.println("</tr>");
				}
			}
			else
			{
				out.println("<tr> <td colspan = '6'> No Data Present</td> </tr>");
			}
		}
		else
		{
			out.println("<tr> <td colspan = '6'> No Data Present</td> </tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
