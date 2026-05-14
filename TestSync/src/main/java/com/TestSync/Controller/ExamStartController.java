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

import com.TestSync.Model.QuestionModel;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/examStartController")
public class ExamStartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =  response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("ExamStart.html");
		rd.include(request, response);
		Optional<Integer> esId = Optional.empty();
		Optional<Integer> totalQuestion = Optional.empty();
		try
		{
			totalQuestion  = Optional.of(Integer.parseInt(request.getParameter("TQ").trim()));
			esId = Optional.of(Integer.parseInt(request.getParameter("es_id").trim()));
		}catch(NumberFormatException ex)
		{
			System.out.println("Problem to load questions: "+ex);
		}
		
		int currentPage = 1;

        int recordsPerPage = 1;

        String page =
            request.getParameter("page");

        if(page != null){

            currentPage =
                Integer.parseInt(page);

        }

        int start = (currentPage - 1) * recordsPerPage;
        
        StudentService es = new StudentServiceImp();
        Optional<List<QuestionModel>> list = es.getQuestions(start,recordsPerPage, esId.get());

        out.println("<form action='submitExam' id='examSubmit' method='POST'>");

        if(list.get().isEmpty())
        {
        	out.println("<h1>Contact to Admin</h1>");
        }
        else
        {
        for(QuestionModel q : list.get()){

            out.println("<div class='question-box mb-4 p-3 '>");

            out.println("<h5 class='fw-bold mb-3'>"+ q.getQuestionText()+ "</h5>");

            out.println("<div class='form-check mb-2'><input class='form-check-input' type='radio' name='q"+q.getQuestionId()+"'value='A'><label class='form-check-label'>"+ q.getOp1()+"</label></div>");

            out.println(
            "<div class='form-check mb-2'> <input class='form-check-input' type='radio' name='q"+q.getQuestionId()+"' value='B'>"

            + "<label class='form-check-label'>"+ q.getOp2()+"</label>"
            + "</div>");

            out.println("<div class='form-check mb-2'>"
            + "<input class='form-check-input' "
            + "type='radio' "
            + "name='q"+q.getQuestionId()+"' "
            + "value='C'>"
            + "<label class='form-check-label'>"
            + q.getOp3()
            + "</label>"
            + "</div>");

            out.println("<div class='form-check mb-2'>"
            + "<input class='form-check-input' "
            + "type='radio' "
            + "name='q"+q.getQuestionId()+"' "
            + "value='D'>"

            + "<label class='form-check-label'>"
            + q.getQuestionId()
            + "</label>"
            + "</div>");

            out.println("</div>");
        }
        out.println("</form>");

        // Pagination
        }
        out.println("<div class='d-flex justify-content-between mt-4'>");

     // Previous Button
        if(currentPage > 1){

            out.println("<a class='btn btn-secondary' "
            + "href='examStartController?"
            + "es_id="
            + esId.get()
            + "&TQ="
            + totalQuestion.get()
            + "&page="
            + (currentPage - 1)
            + "'>"
            + "Previous"
            + "</a>");
        }
        else{

            out.println("<div></div>");

        }
        if(currentPage == totalQuestion.get()){

            out.println("<button type='submit' "
            + "class='btn btn-success'>"
            + "Submit Exam"
            + "</button>");

        }
        else{
            out.println("<a class='btn btn-primary' "
            + "href='examStartController?"
            + "es_id="
            + esId.get()
            + "&TQ="
            + totalQuestion.get()
            + "&page="
            + (currentPage + 1)
            + "'>"
            + "Next"
            + "</a>");
        }

        out.println("</div>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
