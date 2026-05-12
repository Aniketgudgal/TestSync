package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.TestSync.Model.ExamModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/addExamData")
public class AddExamData extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String examName = request.getParameter("examName").trim();
		String totalQuestion = request.getParameter("totalQuestion").trim();
		String totalMakrs = request.getParameter("totalMakrs").trim();
		String examDuration = request.getParameter("examDuration").trim();
		String subjId = request.getParameter("subjId").trim();
		PrintWriter out = response.getWriter();
		out.println(examName);
		out.println(totalQuestion);
		out.println(totalMakrs);
		out.println(examDuration);
		out.println(subjId);
		if(examName.length() > 0 && totalQuestion.length() > 0 && totalMakrs.length() > 0 && examDuration.length() > 0 && subjId.length() > 0)
		{
			Optional<Integer> totalQuestionOp = Optional.empty();
			Optional<Integer> totalMakrsOp = Optional.empty();
			Optional<Integer> examDurationOp = Optional.empty();
			Optional<Integer> subjIdOp = Optional.empty();
			try
			{
				totalQuestionOp = Optional.of(Integer.parseInt(totalQuestion));
				totalMakrsOp = Optional.of(Integer.parseInt(totalMakrs));
				examDurationOp = Optional.of(Integer.parseInt(examDuration));
				subjIdOp = Optional.of(Integer.parseInt(subjId));
			}catch(Exception ex)
			{
				System.out.println("Problem to convert data of exam");
			}
			if(totalQuestionOp.isPresent() && totalMakrsOp.isPresent() && examDurationOp.isPresent() && subjIdOp.isPresent())
			{
				ExamModel em = new ExamModel(0,examName, subjIdOp.get(), totalQuestionOp.get(), totalMakrsOp.get(), examDurationOp.get());
				AdminService as = new AdminServiceImpl();
				if(as.addExam(em))
				{
					out.println("<html>");
					out.println("<body>");
					out.println("<script>");
					out.println("alert('Exam Added  Successfully');");
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
					out.println("alert('Some thing went wrong');");
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
				out.println("alert('Insert valid data');");
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
			out.println("alert('Insert valid data');");
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
