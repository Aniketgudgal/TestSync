package com.TestSync.Controller;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.TestSync.Model.ExamScheduleModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/addExamSchedule")
public class AddExamScheduleController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String startTime = request.getParameter("startTime");
		String Sdate = request.getParameter("Sdate");
		String[] examId = request.getParameter("examId").split("-");
		String courseId = request.getParameter("courseId");
		Optional<Integer> dur = Optional.empty();
		Optional<Integer> subj = Optional.empty();
		Optional<Integer> exId = Optional.empty();
		Optional<Integer> courId = Optional.empty();

		try
		{
			exId = Optional.of(Integer.parseInt(examId[0].trim()));
			dur = Optional.of(Integer.parseInt(examId[1].trim()));
			subj = Optional.of(Integer.parseInt(examId[2].trim()));
			courId = Optional.of(Integer.parseInt(courseId.trim()));
			
		}catch(NumberFormatException ex)
		{
			System.out.println("Problem to convert time: "+ex);
		}
		Optional<LocalTime> st = Optional.empty();
		Optional<LocalTime> et = Optional.empty();
		try
		{
			st = Optional.of(LocalTime.parse(startTime));
			et = Optional.of(st.get().plusMinutes(dur.get()));
		}catch(DateTimeParseException ex)
		{
			System.out.println("Problem to Convert Date"+ex);
		}
		
		
		if((!startTime.isEmpty()) && (!Sdate.isEmpty()) && exId.isPresent() && dur.isPresent() && subj.isPresent() && st.isPresent() && et.isPresent())
		{
			ExamScheduleModel m = new ExamScheduleModel(-1, exId.get(), subj.get(), false, st.get()+"", et.get()+"",Sdate,courId.get());
			AdminService e = new AdminServiceImpl();
			if(e.addExamSchedule(m))
			{
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Schedule Added Successfully');");
				out.println("window.location = 'AdminDashboard.html'");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
			} else {
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('Problem to add Exam Schedule');");
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
			out.println("alert('Invalid Data');");
			out.println("window.location = 'AdminDashboard.html'");
			out.println("</script>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
