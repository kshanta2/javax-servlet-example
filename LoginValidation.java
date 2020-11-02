package com.csg.servlet.assig1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/loginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String URL = null;
	String USER = null;
	String PASS = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		URL = "jdbc:oracle:thin:@localhost:1521:XE";
		USER = "system";
		PASS = "Chiku1._";

		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		String password = request.getParameter("password"); 
		Boolean loggedIn = false;
		Boolean isAdmin=false;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASS);
			String query = "select * from users where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, request.getParameter("userName"));
			ResultSet rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String pass = rs.getString("Password");
					String adminFlag=rs.getString("Usertype");
					if(pass.equalsIgnoreCase(password)) {
						if(adminFlag.equalsIgnoreCase("A")) {
							isAdmin=true;
							loggedIn=true;
						}else {
							isAdmin=false;
							loggedIn=true;
						}
					}
					else {
						loggedIn=false;
					}
				}
			}
			else {
				loggedIn=false;
			}

		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(loggedIn&&isAdmin) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>"+"Admin"+request.getParameter("userName")+" Logged In Successfully"+" "+request.getParameter("name")+"</h1>");
			//out.close();
			response.sendRedirect("AdminPage.html");
			
		}else if (loggedIn&&!isAdmin) {
			response.sendRedirect("SuccessfulLogin.html");
		}
		else {
			response.sendRedirect("Error.html");
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
