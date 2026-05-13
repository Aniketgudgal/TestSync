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
import com.TestSync.Service.CourseService;
import com.TestSync.Service.CourseServiceImpl;
import com.TestSync.Model.CourseModel;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.SubjectModel;
import com.TestSync.Service.AdminService;
@WebServlet("/examSchedule")
public class ExamSchedule extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class = 'container'>");
		
		out.println("<form action = 'addExamSchedule' method = 'GET'>");
		out.println("<div class = 'row mt-3'>");
		out.println("<div class = 'col-6'> <label for='ST' class='form-label'>Start Time</label> </div>");
		out.println("<div class = 'col-6'> <label for='DT'  class='form-label'>Date</label> </div>");
		out.println("</div>");
		
		out.println("<div class = 'row mt-2'>");
		out.println("<div class = 'col-6'> <input type='time' name = 'startTime' class='form-control w-50' id='ST'> </div>");
		out.println("<div class = 'col-6'> <input type='date' name = 'Sdate' class='form-control w-50' id='DT'> </div>");
		out.println("</div>");
		
		out.println("<div class = 'row mt-3'>");
		out.println("<div class = 'col-6'> <label for='EX' class='form-label'>Select Exam</label> </div>");
		out.println("<div class = 'col-6'> <label for='CO' class='form-label'>Select Course</label> </div>");
		out.println("</div>");
		
		out.println("<div class = 'row mt-2'>");
		out.println("<div class = 'col-6'><select name = 'examId' class=' w-50 form-select form-select-lg mb-3'>");
		AdminService as = new AdminServiceImpl();
		Optional<List<ExamModel>> o = as.getExam();
		if(o.isPresent())
		{
			if(!o.get().isEmpty())
			{
				List<ExamModel> list = o.get();
				for(ExamModel al: list)
				{
					out.println("<option value='"+al.getId()+"-"+al.getExamDuration()+"-"+al.getSubjectId()+"'>"+al.getExamName()+"</option>");
				}
			}
			else
			{
				out.println("<option>No Exam Data Present</option>");
			}
		}
		else
		{
			out.println("<option>No Exam Data Present</option>");
		}
		out.println("</select>");
		out.println("</div>");
		out.println("<div class = 'col-6'><select name = 'courseId' class=' w-50 form-select form-select-lg mb-3'>");
		CourseService cs = new CourseServiceImpl();
		Optional<List<CourseModel>> cm = cs.getAllCourses();
		if(o.isPresent())
		{
			List<CourseModel> list = cm.get();
			if(!list.isEmpty())
			{
				for(CourseModel model: list)
				{
					out.println("<option value='"+model.getCourseId()+"'>"+model.getCourseName()+"</option>");
				}
			}
			else
			{
				out.println("<option>No Course Data Present</option>");
			}
		}
		else
		{
			out.println("<option>No Course Data Present</option>");
		}
		out.println("</select>");
		out.println("</div>");
		out.println("<div class = 'row'>");
		out.println("<div class = 'col-4'>");
		out.println("<button class = 'btn btn-primary' onclick = 'return addSchedule()' type = 'submit'>Schedule Exam</button>");
		out.println("</div></div>");
		
		out.println("</form>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
