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

import com.TestSync.Model.SubjectModel;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/viewSubj")
public class ViewSubject extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
	rst.include(request, response);
	AdminServiceImpl as = new AdminServiceImpl();
	Optional<List<SubjectModel>> o = as.getSubject();
	out.println("<table class = 'table  container p-2'>");
	out.println("<thead class = 'table-primary text-center'>");
	out.println("<tr>");
	out.println("<th>Sr No</th>");
	out.println("<th>Subject Name</th>");
	out.println("<th>Update</th>");
	out.println("</tr>");
	out.println("</thead>");
	out.println("<tbody class = 'table-hover text-center'>");
	if(o.isPresent())
	{
		List<SubjectModel> list = o.get();
		if(!list.isEmpty())
		{
			int count = 1;
			for(SubjectModel s: list)
			{
				out.println("<tr>");
				out.println("<td>"+(count++)+"</td>");
				out.println("<td>"+s.getName()+"</td>");
				out.println("<td> <a href = 'UpdateSubj?id="+s.getId()+"'> <img src= 'Images/icons-update.png' alt='not found' width='30' > </a></td>");
				out.println("</tr>");
			}
		}
		else
		{
			out.println("<tr><td colspan = '4'>No Subject Present</td></tr>");
		}
	}
	else
	{
		out.println("<tr><td colspan = '4'>No Subject Present</td></tr>");
	}
	out.print("</tbody>");
	out.println("</table>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
