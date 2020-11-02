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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidationWithCookies
 */
@WebServlet("/LoginValidationWithCookies")
public class LoginValidationWithCookies extends HttpServlet {
	@Override
	public void init() throws ServletException {
		URL = "jdbc:oracle:thin:@localhost:1521:XE";
		USER = "system";
		PASS = "Chiku1._";
		super.init();
	}

	private static final long serialVersionUID = 1L;
	String URL = null;
	String USER = null;
	String PASS = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidationWithCookies() {
    	
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String username=null;
		String password = null; 
		Boolean loggedIn = false;
		Boolean isAdmin=false;
		Cookie[] allCookies = request.getCookies();
		if(allCookies!=null) {
			for(int i = 0;i<2;i++) {
				Cookie temp = allCookies[i];
				if(temp.getName().equalsIgnoreCase("userName")) {
					if(request.getParameter("userName").equalsIgnoreCase(temp.getValue())) {
						username=temp.getValue();
						//cookieResponse=true;
					}else {
						username=request.getParameter("userName");
						password = request.getParameter("password");
						Cookie userName = new Cookie("userName",username);
						Cookie userPassword = new Cookie("userPassword",password);
						response.addCookie(userName);
						response.addCookie(userPassword);
						System.out.println("created Cookies");
						//cookieResponse=false;
					}
				}else if (temp.getName().equalsIgnoreCase("userPassword")) {
					if(request.getParameter("password").equalsIgnoreCase(temp.getValue())) {
						password=temp.getValue();
						//cookieResponse=true;
					}else {
						username=request.getParameter("userName");
						password = request.getParameter("password");
						Cookie userName = new Cookie("userName",username);
						Cookie userPassword = new Cookie("userPassword",password);
						response.addCookie(userName);
						response.addCookie(userPassword);
						System.out.println("created Cookies");
						//cookieResponse=false;
					}
				}
			}
			
			System.out.println("using Cookies");
		}else {
			username=request.getParameter("userName");
			password = request.getParameter("password");
			Cookie userName = new Cookie("userName",username);
			Cookie userPassword = new Cookie("userPassword",password);
			response.addCookie(userName);
			response.addCookie(userPassword);
			System.out.println("created Cookies");
		}
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASS);
			String query = "select * from users where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
