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

import com.TestSync.Service.AdminServiceImpl;
import com.TestSync.Service.AdminService;
@WebServlet("/viewQues")
public class ViewQuestion extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class = 'container'>");
		out.println("<table class = 'table table-hover text-center'>");
		out.println("<thead class = 'table-primary'>");
		out.println("<tr>");
		out.println("<th>Sr No</th>");
		out.println("<th>Subject Name</th>");
		out.println("<th>Question Description</th>");
		out.println("<th>Option 1</th>");
		out.println("<th>Option 2</th>");
		out.println("<th>Option 3</th>");
		out.println("<th>Option 4</th>");
		out.println("<th>Correct Option</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		AdminService as = new AdminServiceImpl();
		Optional<List<Object[]>> o = as.getQuestion();
		if(o.isPresent())
		{
			if(!o.get().isEmpty())
			{
				List<Object[]> list = o.get();
				int count = 1;
				for(Object[] obj: list)
				{
					out.println("<tr>");
					out.println("<td>"+(count++)+"</td>");
					for(Object object: obj)
					{
						out.println("<td>"+object+"</td>");
					}
					out.println("</tr>");
				}
			}
			else
			{
				out.println("<tr> <td colspan = '8'>No Data Present</td> </tr>");
			}
			
		}
		else
		{
			out.println("<tr> <td colspan = '8'>No Data Present</td> </tr>");
		}
		out.println("<tbody>");
		out.println("</table>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
