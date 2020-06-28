package com.jumia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd =null;
		if ("admin".equals(request.getParameter("username")) && "password".equals(request.getParameter("password"))) {
			HttpSession hs = request.getSession();
			hs.setAttribute("user", "admin");
			response.sendRedirect("admin_home.jsp");
		}else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Wrong Credentials, try again...')</script>\n" + "    ");
			rd = request.getRequestDispatcher("admin_login.jsp");
			rd.include(request, response);
		}
	}
}
