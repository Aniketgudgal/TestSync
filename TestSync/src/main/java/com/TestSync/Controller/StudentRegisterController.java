package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.io.PrintWriter;

import com.TestSync.Model.StudentModel;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

/**
 * Servlet implementation class StudentRegisterController
 */
@WebServlet("/registerstudent")
public class StudentRegisterController extends HttpServlet { 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String btnRegister = request.getParameter("btnRegister");
		int cnt = 0;
		if(btnRegister != null)
		{
			String name = request.getParameter("studName");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int courseId = Integer.parseInt(request.getParameter("subjectId"));
			String mobile = request.getParameter("mobile");
			
			StudentModel student= new StudentModel(cnt,name,email,username,password,courseId,mobile);
			StudentService service = new StudentServiceImp();
			boolean result = service.idAddedRecord(student);
			if (result) {
				out.println(
						"<html><body><script>" + "alert('Student Registered Successfully....!'); window.location = 'StudentLogin.html</script></body></html>");
			} else {

				out.println("<html><body><script>"
						+ "alert('Something Went Wrong....?'); window.location = 'StudentDashboard.html'</script></body></html>");
			}
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
