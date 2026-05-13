package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.TestSync.Model.QuestionModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/addQuesControl")
public class AddQuestionController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =  response.getWriter();
		String examId = request.getParameter("examId").trim();
		String question = request.getParameter("question").trim();
		String OP1 = request.getParameter("OP1").trim();
		String OP2 = request.getParameter("OP2").trim();
		String OP3 = request.getParameter("OP3").trim();
		String OP4 = request.getParameter("OP4").trim();
		String correctOp = request.getParameter("radioValue").trim();
		Optional<Integer> o = Optional.empty();
		try
		{
			o = Optional.of(Integer.parseInt(examId));
		}catch(NumberFormatException ex)
		{
			System.out.println("problem to convert number: "+ex);
		}
		correctOp = request.getParameter(correctOp).trim();
		if(o.isPresent() && question.length() > 1 && OP1.length() > 1 && OP2.length() > 1 && OP3.length() > 1 && OP4.length() > 1)
		{
			QuestionModel m = new QuestionModel(-1, o.get(), question, OP1, OP2, OP3, OP4, correctOp);
			AdminService as = new AdminServiceImpl();
			if(as.addQuestion(m))
			{
				out.println("<html> <body>");
				out.println("<script>");
				out.println("alert('Question Added Successfully'); window.location = 'AdminDashboard.html'");
				out.println("</script>");
				out.println("</body></html>");
			}
			else
			{
				out.println("<html> <body>");
				out.println("<script>");
				out.println("alert('Problem to add'); window.location = 'AdminDashboard.html'");
				out.println("</script>");
				out.println("</body></html>");
			}
		}
		else
		{
			out.println("<html> <body>");
			out.println("<script>");
			out.println("alert('Invalid Data'); window.location = 'addQuestion'");
			out.println("</script>");
			out.println("</body></html>");
		}
		out.println(question);
		out.println(OP1);
		out.println(OP2);
		out.println(OP3);
		out.println(OP4);
		out.println(correctOp);
		out.println(examId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
