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
import java.util.Optional;

import com.TestSync.Model.AdminModel;
import com.TestSync.Service.AdminService;
import com.TestSync.Service.AdminServiceImpl;

/**
 * Servlet implementation class UpdateAdminProfile
 */
@WebServlet("/updateadminprofile")
public class UpdateAdminProfile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		RequestDispatcher r = request.getRequestDispatcher("viewadmin");
		r.include(request, response);

		String name = request.getParameter("adminName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		HttpSession session = request.getSession();
//		int id = (Integer) session.getAttribute("adminId");
		String id = request.getParameter("admin_Id").trim();
		Optional<Integer> o = Optional.empty();
		try {
			o = Optional.of(Integer.parseInt(id));
		} catch (Exception e) {
			System.out.println("Problem to convert: "+e);
		}
		if (o.isPresent()) {
			AdminModel adminModel = new AdminModel();
			adminModel.setName(name);
			adminModel.setEmail(email);
			adminModel.setPassword(password);
			adminModel.setId(o.get());	

			AdminService adminService = new AdminServiceImpl();
			boolean result = adminService.updateAdminProfile(adminModel);
			if (result) {
				out.println("<html><body><script>"
						+ "alert('Profile Updated Successfully....!'); window.location = 'viewadmin'</script></body></html>");

			} else {

				out.println("<html><body><script>"
						+ "alert('Something Went Wrong....?'); window.location = 'viewadmin'</script></body></html>");
			}
		}else {

			out.println("<html><body><script>"
					+ "alert('Something Went Wrong....?'); window.location = 'AdminDashboard.html'</script></body></html>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
