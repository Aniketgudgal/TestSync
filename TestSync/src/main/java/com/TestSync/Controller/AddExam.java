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
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/addExam")
public class AddExam extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class = 'container'>");
		out.println("<form action = 'addExamData' method = 'POST' class = 'form-group'>");
		//first row
		out.println("<div = 'row d-flex align-items-center'>");
		//first column
		out.println("<div class = 'col-12 d-flex'>");
		out.println("<div class= 'col-6'> <label for='examName' class='col-form-label'>Exam Name</label> </div>");
		out.println("<div class= 'col-6'> <label for='TQ' class='col-form-label'>Total Questions</label> </div>");
		out.println("</div>");	
		out.println("</div>");
		//second row
		out.println("<div = 'row d-flex align-items-center'>");
		out.println("<div class = 'col-12 d-flex'>");
		out.println("<div class= 'col-6'> <input class = 'form-control w-75' name = 'examName' type = 'text' value = ''> </div>");
		out.println("<div class= 'col-6'> <input class = 'form-control w-75' type = 'number' name = 'totalQuestion' value = ''> </div>");
		out.println("</div>");	
		out.println("</div>");
		
		out.println("<div = 'row d-flex align-items-center'>");
		//first column
		out.println("<div class = 'col-12 d-flex'>");
		out.println("<div class= 'col-6'> <label for='tM' class='col-form-label'>Total Marks</label> </div>");
		out.println("<div class= 'col-6'> <label for='ET' class='col-form-label'>Exam Duration</label> </div>");
		out.println("</div>");	
		out.println("</div>");
		
		//second row
		out.println("<div = 'row d-flex align-items-center'>");
		out.println("<div class = 'col-12 d-flex'>");
		out.println("<div class= 'col-6'> <input class = 'form-control w-75' type = 'number' name = 'totalMakrs' value = ''> </div>");
		out.println("<div class= 'col-6'> <input class = 'form-control w-75' type = 'number' name = 'examDuration' value = ''> </div>");
		out.println("</div>");	
		out.println("</div>");
		
		out.println("<div = 'row d-flex align-items-center'>");
		//first column
		out.println("<div class = 'col-12 d-flex'>");
		out.println("<div class= 'col-12'> <label for='tM' class='col-form-label'>Select Subject</label> </div>");
		out.println("</div>");	
		out.println("</div>");
		
		out.println("<div = 'row d-flex align-items-center'>");
		out.println("<div class = 'col-12 d-flex'>");
		out.println("<div class= 'col-12'>");
		out.println("<select name = 'subjId' class='form-select w-25' aria-label='Default select example'>");
		
		AdminService admin = new AdminServiceImpl();
		Optional<List<SubjectModel>> o = admin.getSubject();
		if(o.isPresent())
		{
			List<SubjectModel> list = o.get();
			for(SubjectModel m: list)
			{
				out.println("<option value='"+m.getId()+"'>"+m.getName()+"</option>");
			}
		}
		else
		{
			out.println("<option selected>No Subject Present</option>");
		}
		
		out.println("</select>");
		out.println("</div>");
		out.println("</div>");	
		out.println("</div>");
		
		out.println("<div = 'row d-flex align-items-center'>");
		out.println("<div class = 'col-12 w-50 mt-4'> <button class = 'btn btn-primary' type = 'submit'>Add Exam</button>");
		out.println("</div>");
		out.println("</div>");
		
		out.println("</form>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
