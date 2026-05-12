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

import com.TestSync.Model.ExamModel;
import com.TestSync.Model.SubjectModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

@WebServlet("/addQuestion")
public class AddQuestions extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rst = request.getRequestDispatcher("AdminDashboard.html");
		rst.include(request, response);
		out.println("<div class = 'container'>");
		out.println("<form action = '' method = 'GET'>");

		// start row label
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		out.println("<label for='exam' class='col-form-label'>Exam</label>");
		out.println("</div>");
		// second col-6
		out.println("<div class = col-6>");
		out.println("<label for='QT' class='col-form-label'>Question Description</label>");
		out.println("</div>");
		out.println("</div>"); // end row

		// input row
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		// access exam data
		out.println("<select name = 'examId' class='form-select w-25' aria-label='Default select example'>");

		AdminService admin = new AdminServiceImpl();
		Optional<List<ExamModel>> o = admin.getExam();
		if (o.isPresent()) {
			List<ExamModel> list = o.get();
			for (ExamModel m : list) {
				out.println("<option value='" + m.getId() + "'>" + m.getExamName() + "</option>");
			}
		} else {
			out.println("<option selected>No Subject Present</option>");
		}

		out.println("</select>");

		out.println("</div>");
		// second col-6
		out.println("<div class = col-6>");
		out.println(
				"<textarea name='question' class='form-control' rows='4'  placeholder='Enter Question'> </textarea>");
		out.println("</div>");
		out.println("</div>"); // end row

		// start row label
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		out.println("<label for='exam' class='col-form-label'>Option 1</label>");
		out.println("</div>");
		// second col-6
		out.println("<div class = col-6>");
		out.println("<label for='QT' class='col-form-label'>Option 2</label>");
		out.println("</div>");
		out.println("</div>"); // end row

		// input row
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		out.println("<input type='text' name = 'OP1' value = '' class='form-control'>");
		out.println("</div>");
		// second col-6
		out.println("<div class = col-6>");
		out.println("<input type='text' name = 'OP2' value = '' class='form-control'>");
		out.println("</div>");
		out.println("</div>"); // end row

		// start row label
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		out.println("<label for='exam' class='col-form-label'>Option 3</label>");
		out.println("</div>");
		// second col-6
		out.println("<div class = col-6>");
		out.println("<label for='QT' class='col-form-label'>Option 4</label>");
		out.println("</div>");
		out.println("</div>"); // end row

		// input row
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		out.println("<input type='text' name = 'OP3' value = '' class='form-control'>");
		out.println("</div>");
		// second col-6
		out.println("<div class = col-6>");
		out.println("<input type='text' name = 'OP4' value='' class='form-control'>");
		out.println("</div>");
		out.println("</div>"); // end row

		// start row label
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		out.println("<label for='exam' class='col-form-label'>Correct Option</label>");
		out.println("</div>");
		out.println("</div>"); // end row

		// input row
		out.println("<div class = 'row'>");
		// first col-6
		out.println("<div class = 'col-6'>");
		// access exam data
		out.println("<input type='text' name = 'COP'  value = ''class='form-control'>");
		out.println("</div>");
		// second col-6
		out.println("</div>"); // end row

		out.println("<div class = 'row mt-4'>");
		out.println("<div class = col-6>");
		out.println("<button type = 'submit' class = 'btn btn-primary'>Add Question</button>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
