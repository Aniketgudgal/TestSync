package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.TestSync.Model.SubjectModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/AddSubject")
public class AddSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String subjName = request.getParameter("subjectName").trim();
		out.println(subjName);
		if(subjName != null && subjName.length() > 1)
		{
			SubjectModel model = new SubjectModel();
			model.setName(subjName);
			AdminService as = new AdminServiceImpl();
			if(as.addSubject(model))
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Subject Added Successfully');");
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
				out.println("alert('Problem to Add Subject');");
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
			out.println("alert('Invalid Data');");
			out.println("window.location = 'AdminDashboard.html'");
			out.println("</script>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
