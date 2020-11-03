package com.csg.servlet.assig1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowMessageBasedOnCookies
 */
@WebServlet("/ShowMessageBasedOnCookies")
public class ShowMessageBasedOnCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMessageBasedOnCookies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = null;
		String password = null;
		Boolean cookieResponse = false;
		Cookie[] allCookies = request.getCookies();
		if (allCookies != null) {
			for (int i = 0; i < allCookies.length; i++) {
				Cookie temp = allCookies[i];
				System.out.println("Cookie:" + temp.getName());
				if (temp.getName().equalsIgnoreCase(request.getParameter("userName"))) {
					
					username = temp.getName();
					password = temp.getValue();
					System.out.println("username: " + username + "password: " + password);
					cookieResponse = true;
				} else {
					System.out.println("Invalid match failed");
					cookieResponse = false;
				}
			}
			if (!cookieResponse) {
				username = request.getParameter("userName");
				password = request.getParameter("password");
				Cookie userName = new Cookie(username, password);

				response.addCookie(userName);

				System.out.println("created Cookies" + userName.getName());
				cookieResponse = false;
			}

		} else {
			username = request.getParameter("userName");
			password = request.getParameter("password");
			Cookie userName = new Cookie(username, password);

			response.addCookie(userName);

			System.out.println("created Cookies");
			cookieResponse = false;
		}
		

		if (cookieResponse) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + "welcome back" + "</h1>");

		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + "Welcome to new user" + "</h1>");

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
