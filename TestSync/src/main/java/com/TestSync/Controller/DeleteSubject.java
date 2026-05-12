package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/DeleteSubj")
public class DeleteSubject extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(request.getParameter("id"));
		String strId = request.getParameter("id");
		Optional<Integer> num = Optional.empty();
		try
		{
			num = Optional.of(Integer.parseInt(strId));
		}catch(Exception ex)
		{
			System.out.println("Problem to convert data: "+ex);
		}
		if(num.isPresent())
		{
			AdminServiceImpl as = new AdminServiceImpl();
			if(as.deleteSubject(num.get()))
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Subject Delete Suceessfully');");
				out.println("window.location = 'AdminDashboard.html'");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
			}
			else
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Problem to delete');");
				out.println("window.location = 'AdminDashboard.html'");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		else
		{
			out.println("<html>");
			out.println("<body>");
			out.println("<script>");
			out.println("alert('Problem to Delete');");
			out.println("window.location = 'AdminDashboard.html'");
			out.println("</script>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
