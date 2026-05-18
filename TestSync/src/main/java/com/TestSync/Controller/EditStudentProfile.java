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

import com.TestSync.Model.StudentModel;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/editstudentprofile")
public class EditStudentProfile extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 
		 HttpSession session = request.getSession(false);

	        if (session == null || session.getAttribute("sId") == null) {
	            response.sendRedirect("StudentLogin.html");
	            return;
	        }

	     int id = (Integer) session.getAttribute("sId");
		 String name = request.getParameter("name");
		 String email = request.getParameter("email");
		 String username = request.getParameter("username");
		 String mobile = request.getParameter("mobile"); 
		 
		 if(name != null && email != null && username != null && mobile != null)
		 {
			 StudentModel model = new StudentModel();
			 model.setId(id);
			 model.setName(name);
			 model.setEmail(email);
			 model.setUserName(username);
			 model.setMobile(mobile);
			 
			 StudentService studentService = new StudentServiceImp();
			 boolean result = studentService.updateStudentProfile(model);
				if (result) {
					out.println("<html><body><script>" + "alert('Profile Updated Successfully....!'); window.location = 'viewstudent'</script></body></html>");
					 
					
				} else {

					out.println("<html><body><script>"
							+ "alert('Something Went Wrong....?'); window.location = 'editstudentprofile'</script></body></html>");
				}
		 }
		 else {
			 out.println("Error in edit update profile ");
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
