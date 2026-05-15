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
import java.util.List;
import java.util.Optional;

import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/studenExamSchedulePending")
public class StudenExamSchedulePending extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("StudentDashboard.html");
		rst.include(request, response);
		HttpSession session = request.getSession();
		Optional<Integer> id = Optional.empty();
		try
		{
			id = Optional.of((int)session.getAttribute("sId"));
		}catch(Exception ex)
		{
			System.out.println("Problem to convert session id of student: "+ex);
		}
		if(id.isPresent())
		{
			StudentService ss = new StudentServiceImp();
			Optional<List<Object[]>> o = ss.getExamScheduleInfoPending(id.get());
			if(o.isPresent())
			{
				out.println("<div class = 'container'>");
				out.println("<table class = 'table text-center table-hover'>");
				out.println("<thead class = 'table-primary'>");
				out.println("<tr>");
				out.println("<td> Sr No</td>");
				out.println("<td>Course Name</td>");
				out.println("<td>Exam Name</td>");
				out.println("<td>Subject Name</td>");
				out.println("<td>Start Time</td>");
				out.println("<td>End Time</td>");
				out.println("<td>Date</td>");
				out.println("<td>Total Questions</td>");
				out.println("<td>Total Marks</td>");
				out.println("<td>Exam</td>");
				out.println("<td>Attempt</td>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				List<Object[]> list = o.get();
				if(!list.isEmpty())
				{
					int count = 1;
					System.out.println(list);
					for(Object[] obj: list)
					{
						out.println("<tr>");
						out.println("<td>"+(count++)+"</td>");
						int totalQ=0;
						for(int i = 0; i < obj.length; i++)
						{
							if(i == obj.length-4)
							{
								try
								{
									totalQ = Integer.parseInt(obj[i].toString());
								}catch(Exception ex)
								{
									System.out.println("problem to convert object string int: "+ex);
								}
							}
							if(i == obj.length-1)
							{
								out.println("<td class = 'p-2'>");
								out.println("<a class  = 'btn btn-primary' href = 'examStartController?TQ="+totalQ+"&es_id="+obj[i]+"'>Start Exam</a>");
								out.println("</td>");
								continue;
							}
							out.println("<td>"+obj[i]+"</td>");
						}
						out.println("</tr>");
					}
				}
				else
				{
					out.println("<tr>");
					out.println("<td colspan = '11'>No Exam Schedule</td>");
					out.println("</tr>");
				}
				out.println("</tbody>");
				out.println("</table>");
				out.println("</div>");
			}
			else
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Problem to accee data');");
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
			out.println("alert('Problem to accee data');");
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
