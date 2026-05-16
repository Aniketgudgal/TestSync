package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.TestSync.Model.CourseModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

/**
 * Servlet implementation class AddCourseController
 */
@WebServlet("/AddCourse")
public class AddCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String courseName = request.getParameter("courseName").trim();
		
		if(courseName != null && courseName.length() > 1)
		{
			CourseModel model = new CourseModel();
			model.setCourseName(courseName);
			AdminService admin = new AdminServiceImpl();
			if(admin.addCourse(model))
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Course Added Successfully');");
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
				out.println("alert('Problem to Add Course');");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
