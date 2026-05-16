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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.TestSync.Model.QuestionModel;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/examStartController")
public class ExamStartController extends HttpServlet {
//	Map<Integer, String> al;
//	Map<Integer, String> choose;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        RequestDispatcher rd = request.getRequestDispatcher("ExamStart.html");
        rd.include(request, response);

        Optional<Integer> esId = Optional.empty();

        Optional<Integer> totalQuestion = Optional.empty();

        try {

            totalQuestion = Optional.of(Integer.parseInt(request.getParameter("TQ").trim()));

            esId = Optional.of(Integer.parseInt(request.getParameter("es_id").trim()));

        } catch (Exception e) {

            System.out.println("Error : " + e);
        }

        int currentPage = 1;

        int recordsPerPage = 1;

        String page = request.getParameter("page");

        if (page != null) {

            currentPage = Integer.parseInt(page);
        }

        int start = (currentPage - 1) * recordsPerPage;

        // SESSION
        HttpSession session = request.getSession();
    
        int studentId = (Integer)session.getAttribute("sId");
        session.setAttribute("sId", studentId);
        // CORRECT ANSWER MAP
        Map<Integer, String> correctAnswer = (Map<Integer, String>)session.getAttribute("correctAnswer");

        if (correctAnswer == null) {

            correctAnswer = new LinkedHashMap<>();
        }

        // STUDENT ANSWER MAP
        Map<Integer, String> studentAnswer = (Map<Integer, String>)session.getAttribute("studentAnswer");

        if (studentAnswer == null) {

            studentAnswer = new LinkedHashMap<>();
        }

        // SAVE PREVIOUS QUESTION ANSWER
        String previousQid = request.getParameter("previousQid");

        if (previousQid != null) {

            int qid = Integer.parseInt(previousQid);

            String selectedAns = request.getParameter("q" + qid);

            if (selectedAns != null) {

                studentAnswer.put(qid, selectedAns);
            }
        }

        // SAVE IN SESSION
        session.setAttribute("studentAnswer", studentAnswer);

        StudentService es = new StudentServiceImp();

        Optional<List<QuestionModel>> list = es.getQuestions(start, recordsPerPage, esId.get());

        out.println("<div>");

        out.println("<form action='examStartController' method='POST'>");

        if (list.get().isEmpty()) {

            out.println("<h1>No Questions Found</h1>");

        } else {

            for (QuestionModel q : list.get()) {

                // STORE CORRECT ANSWER
                correctAnswer.put(q.getQuestionId(), q.getCorrectOp());

                out.println("<div class='question-box mb-4 p-3'>");

                out.println("<h5 class='fw-bold mb-3'>"+ q.getQuestionText()+ "</h5>");

                // HIDDEN QUESTION ID
                out.println("<input type='hidden' "+ "name='previousQid' "+ "value='"+ q.getQuestionId()+ "'>");

                // OPTION 1
                out.println("<div class='form-check mb-2'>");

                out.println("<input class='form-check-input' id = 'Op1' type='radio' required name='q" + q.getQuestionId() + "'"+ "value='" + q.getOp1() + "'>");

                out.println("<label for='Op1' class='form-check-label'>"+ q.getOp1()+ "</label>");

                out.println("</div>");

                // OPTION 2
                out.println("<div class='form-check mb-2'>");

                out.println("<input class='form-check-input' id = 'Op2' type='radio' required name='q" + q.getQuestionId() + "' "+ "value='" + q.getOp2() + "'>");

                out.println("<label for = 'Op2' class='form-check-label'>"+ q.getOp2() + "</label>");

                out.println("</div>");

                // OPTION 3
                out.println("<div class='form-check mb-2'>");

                out.println("<input id = 'Op3' class='form-check-input' "+ "type='radio' required name='q" + q.getQuestionId() + "' "+ "value='" + q.getOp3() + "'>");

                out.println("<label for = 'Op3' class='form-check-label'>"+ q.getOp3()+ "</label>");

                out.println("</div>");

                // OPTION 4
                out.println("<div class='form-check mb-2'>");

                out.println("<input id = 'Op4' class='form-check-input' type='radio' required name='q" + q.getQuestionId() + "' value='" + q.getOp4() + "'>");

                out.println("<label for = 'Op4' class='form-check-label'>"+ q.getOp4()+ "</label>");

                out.println("</div>");

                out.println("</div>");
            }

            // SAVE CORRECT ANSWERS
            session.setAttribute("correctAnswer", correctAnswer);
//
//            System.out.println("Correct Answer : "+ correctAnswer);
//
//            System.out.println("Student Answer : "+ studentAnswer);

            // HIDDEN FIELDS
            out.println("<input type='hidden' name='es_id' value='"+ esId.get()+ "'>");

            out.println("<input type='hidden' name='TQ' value='"+ totalQuestion.get()+ "'>");

            out.println("<div class='d-flex justify-content-between mt-4'>");

            // PREVIOUS BUTTON
            if (currentPage > 1) {

                out.println("<button type='submit' name='page' value='"+ (currentPage - 1)+"' class='btn btn-secondary'>Previous</button>");
            }

            // SUBMIT BUTTON
            if (currentPage == totalQuestion.get()) {
                out.println("<button formaction='endExamController' type='submit' name = 'es_id' value = '"+esId.get()+"' class='btn btn-success'>Submit Exam</button>");

            } else {

                out.println("<button type='submit' name='page' value='"+ (currentPage + 1)+ "' class='btn btn-primary'>Next</button>");
            }

            out.println("</div>");
        }

        out.println("</form>");

        out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
