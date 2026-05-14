package com.TestSync.Controller;

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
import com.mysql.cj.xdevapi.DatabaseObject.DbObjectType;

@WebServlet("/GetCourseController")
public class CourseController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		CourseService courseService = new CourseServiceImpl();
		Optional<List<CourseModel>> optionalList = courseService.getAllCourses();
		
		 if(optionalList.isPresent())
		 {
			 List<CourseModel> list = optionalList.get();
			 
			 for(CourseModel c:list)
			 {
				 out.println("<option value='"+c.getCourseId()+"'>"+c.getCourseName()+"</option>");
			 }
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
