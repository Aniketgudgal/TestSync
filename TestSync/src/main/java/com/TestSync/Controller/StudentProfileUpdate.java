package com.TestSync.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/stdUpdatePro")
public class StudentProfileUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String strId = (String)session.getAttribute("sId");
		Optional<Integer> id = Optional.empty();
		try
		{
			id = Optional.of(Integer.parseInt(strId.trim()));
		}catch(NumberFormatException ex)
		{
			System.out.println("Id Conversion: "+ex);
		}
		if(id.isPresent())
		{
			
		}
		else
		{
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
