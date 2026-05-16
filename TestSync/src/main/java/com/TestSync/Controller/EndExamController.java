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
import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

import com.TestSync.Model.ExamModel;
import com.TestSync.Model.ExamScheduleModel;
import com.TestSync.Model.ResultModel;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

/**
 * Servlet implementation class EndExamController
 */
@WebServlet("/endExamController")
public class EndExamController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        HttpSession session = request.getSession();
	        Optional<Integer> studentId = Optional.empty();
	        try
	        {
	        	studentId = Optional.of((Integer)session.getAttribute("sId"));
	        }catch(NullPointerException ex)
	        {
	        	System.out.println("student Session id is null: "+ex);
	        }
	        Map<Integer, String> correctAnswer = (Map<Integer, String>)session.getAttribute("correctAnswer");
	        Map<Integer, String> studentAnswer = (Map<Integer, String>)session.getAttribute("studentAnswer");
	        
	        String previousQid = request.getParameter("previousQid");
	        Optional<Integer> esId = Optional.empty();
	        try
	        {
	        	esId = Optional.of(Integer.parseInt(request.getParameter("es_id").trim()));
	        }catch(NumberFormatException ex)
	        {
	        	System.out.println("result calculate problem: "+ex);
	        }
	        if (previousQid != null) {

	            int qid = Integer.parseInt(previousQid);

	            String selectedAns =
	                    request.getParameter("q" + qid);

	            if (selectedAns != null) {

	                studentAnswer.put(qid, selectedAns);
	            }
	        }
	        session.setAttribute("studentAnswer",studentAnswer);

	        int marks = 0;

	        for (Integer qid : correctAnswer.keySet()) {

	            String correct =
	                    correctAnswer.get(qid);

	            String student =
	                    studentAnswer.get(qid);

	            if (correct != null && student != null && correct.equals(student)) {
	                marks++;
	            }
	        }
	        StudentService ss = new StudentServiceImp();
	        if(esId.isPresent())
	        {
	        	Optional<ExamScheduleModel>o = ss.getExamSchedule(esId.get());
	        	if(o.isPresent())
	        	{
	        		Optional<ExamModel> ex = ss.getExam(o.get().getExamId());
	        		if(ex.isPresent())
	        		{
	        			int obtainMarks = (marks*ex.get().getTotalMarks()) /ex.get().getTotalQuestions();
	        			double percentage = (obtainMarks*100.0)/ex.get().getTotalMarks();
	        			ResultModel rm = new ResultModel(0, esId.get(), studentId.get(),obtainMarks, (float)percentage, percentage > 36 ? true:false);
	        			boolean result = ss.addResult(rm);
	        			if(result)
	        			{
	        					out.println("<html>");
								out.println("<body>");
								out.println("<script>");
								out.println("alert('Exam Submit...');");
								out.println("window.location = 'StudentDashboard.html'");
								out.println("</script>");
								out.println("</body>");
								out.println("</html>");
	        			}
	        			else
	        			{
	        				out.println("<html>");
							out.println("<body>");
							out.println("<script>");
							out.println("alert('Problem to submit Exam');");
							out.println("window.location = 'StudentDashboard.html'");
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
						out.println("alert('Problem to submit Exam');");
						out.println("window.location = 'StudentDashboard.html'");
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
					out.println("alert('Problem to submit Exam');");
					out.println("window.location = 'StudentDashboard.html'");
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
				out.println("alert('Problem to submit Exam');");
				out.println("window.location = 'StudentDashboard.html'");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
	        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
