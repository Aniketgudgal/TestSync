package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/getExamStartTime")
public class GetExamStartTime extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("es_id");
		Optional<Integer> eId = Optional.empty();
		try
		{
			eId = Optional.of(Integer.parseInt(id));
		}catch(NumberFormatException ex)
		{
			System.out.println("Problem to convert data: "+ex);
		}
		if(eId.isPresent())
		{
			StudentService ss = new StudentServiceImp();
			Optional<String> o = ss.getStartTime(eId.get());
			if(o.isPresent())
			{
				out.print(o.get().trim());
				//System.out.println(o.get());
			}
			else
			{
				out.print("0");
			}
		}
		else
		{
			out.print("0");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
