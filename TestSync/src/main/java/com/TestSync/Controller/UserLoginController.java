package com.TestSync.Controller;

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

@WebServlet("/UserLoginS")
public class UserLoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uName = request.getParameter("uName");
		String password = request.getParameter("password");
		StudentModel st = new StudentModel();
		st.setUserName(uName);
		st.setPassword(password);
		StudentService ss = new StudentServiceImp();
		int id = ss.isRegister(st);
		if(id != -1)
		{
			HttpSession session = request.getSession();
			session.setAttribute("sId", id);
			out.println("Login Success");
		}
		else
		{
			out.println("Invalid Student");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
