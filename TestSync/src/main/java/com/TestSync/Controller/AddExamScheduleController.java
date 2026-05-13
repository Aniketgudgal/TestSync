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
import java.util.Optional;

import com.TestSync.Model.ExamScheduleModel;

@WebServlet("/addExamSchedule")
public class AddExamScheduleController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String startTime = request.getParameter("startTime");
		String Sdate = request.getParameter("Sdate");
		String[] examId = request.getParameter("examId").split("-");
		Optional<Integer> dur = Optional.empty();
		Optional<Integer> subj = Optional.empty();
		Optional<Integer> exId = Optional.empty();
		try
		{
			exId = Optional.of(Integer.parseInt(examId[0].trim()));
			dur = Optional.of(Integer.parseInt(examId[1].trim()));
			subj = Optional.of(Integer.parseInt(examId[2].trim()));
			
		}catch(NumberFormatException ex)
		{
			System.out.println("Problem to convert time: "+ex);
		}
		LocalTime st = LocalTime.parse(startTime);
		LocalTime et = st.plusMinutes(dur.get());
		out.println("<h1>"+et+"</h1>");
		out.println(startTime);
		out.println(Sdate);
		out.println(examId[0]);
		out.println(examId[1]);
		
		ExamScheduleModel m = new ExamScheduleModel(-1, exId.get(), subj.get(), false, st+"", et+"",Sdate);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
