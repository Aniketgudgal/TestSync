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

/**
 * Servlet implementation class UpdateStudentLoggedProfile
 */
@WebServlet("/updateloggedstudent")
public class UpdateStudentLoggedProfile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher r = request.getRequestDispatcher("StudentDashboard.html");
        r.include(request, response);

        StudentServiceImp studentService = new StudentServiceImp();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sId") == null) {
            response.sendRedirect("StudentLogin.html");
            return;
        }

        int id = (Integer) session.getAttribute("sId");

        Optional<List<Object[]>> list = studentService.getStudentById(id);

        out.println("<div class='container mt-4'>");
        out.println("<div class='card shadow-lg'>");

        out.println("<div class='card-header bg-dark text-white text-center'>");
        out.println("<h4>Update Profile</h4>");
        out.println("</div>");

        out.println("<div class='card-body p-4'>");

        if (list.isPresent() && !list.get().isEmpty()) {

            Object[] obj = list.get().get(0);

            out.println("<form method='post' action='editstudentprofile'>");

            out.println("<div class='mb-3'>");
            out.println("<label class='form-label'>Student Name</label>");
            out.println("<input type='text' name='name' value='" + obj[0] + "' class='form-control'/>");
            out.println("</div>");

            out.println("<div class='mb-3'>");
            out.println("<label class='form-label'>Email</label>");
            out.println("<input type='text' name='email' value='" + obj[1] + "' class='form-control'/>");
            out.println("</div>");

            out.println("<div class='mb-3'>");
            out.println("<label class='form-label'>Username</label>");
            out.println("<input type='text' name='username' value='" + obj[2] + "' class='form-control'/>");
            out.println("</div>");

            out.println("<div class='mb-3'>");
            out.println("<label class='form-label'>Course</label>");
            out.println("<input type='text' name='course' value='" + obj[3] + "' class='form-control' readonly/>");
            out.println("</div>");

            out.println("<div class='mb-3'>");
            out.println("<label class='form-label'>Mobile No</label>");
            out.println("<input type='text' name='mobile' value='" + obj[4] + "' class='form-control'/>");
            out.println("</div>");

            out.println("<button type='submit' class='btn btn-success'>Update Profile</button>");

            out.println("</form>");

        } else {
            out.println("<h5 class='text-danger text-center'>No Record Found</h5>");
        }

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
