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

import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;
import com.TestSync.Service.StudentService;
import com.TestSync.Service.StudentServiceImp;

@WebServlet("/viewstudent")
public class ViewStudent extends HttpServlet {

   

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        RequestDispatcher r = request.getRequestDispatcher("StudentDashboard.html");
        r.include(request, response);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("sId") == null) {
            response.sendRedirect("StudentLogin.html");
            return;
        }

        int id = (Integer) session.getAttribute("sId");
        StudentServiceImp studentService = new StudentServiceImp();
        Optional<List<Object[]>> list = studentService.getStudentById(id);

        out.println("<div class='container mt-4'>");
        out.println("<div class='card shadow-lg'>");

        out.println("<div class='card-header bg-dark text-white text-center'>");
        out.println("<h4>My Profile</h4>");
        out.println("</div>");

        out.println("<div class='card-body p-0'>");
        out.println("<div class='table-responsive'>");

        out.println("<table class='table table-bordered table-hover text-center mb-0'>");

        out.println("<thead class='table-dark'>");
        out.println("<tr>");
        out.println("<th>SR NO</th>");
        out.println("<th>STUDENT NAME</th>");
        out.println("<th>EMAIL</th>");
        out.println("<th>USERNAME</th>");
        out.println("<th>COURSE</th>");
        out.println("<th>MOBILE NO</th>");
        out.println("<th>UPDATE</th>");
        out.println("</tr>");
        out.println("</thead>");

        out.println("<tbody>");

        int count = 1;

        if (list.isPresent() && !list.get().isEmpty()) {

            for (Object[] obj : list.get()) {

                out.println("<tr>");

                out.println("<td>" + count + "</td>");
                out.println("<td>" + obj[0] + "</td>");
                out.println("<td>" + obj[1] + "</td>");
                out.println("<td>" + obj[2] + "</td>");
                out.println("<td>" + obj[3] + "</td>");
                out.println("<td>" + obj[4] + "</td>");

                out.println("<td>");
                out.println("<a href='updateloggedstudent' class='btn btn-warning btn-sm'>Edit</a>");
                out.println("</td>");

                out.println("</tr>");

                count++;
            }

        } else {
            out.println("<tr>");
            out.println("<td colspan='7' class='text-danger'>No Record Found</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
