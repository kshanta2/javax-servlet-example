package com.csg.servlet.assig1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTrainingDetails
 */
@WebServlet("/AddTrainingDetails")
public class AddTrainingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String URL = null;
	String USER = null;
	String PASS = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTrainingDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String password = request.getParameter("password"); 
		Boolean loggedIn = false;
		Boolean isAdmin=false;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASS);
			String query = "insert into training_details values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, request.getParameter("TrainingID"));
			pstmt.setString(2, request.getParameter("TrainingName"));
			pstmt.setString(3, request.getParameter("start_date"));
			pstmt.setString(4, request.getParameter("end_date"));
			pstmt.setString(5, request.getParameter("TrainingMode"));
			pstmt.setString(6, request.getParameter("BusinisessUnit"));
			pstmt.setString(7, request.getParameter("ContactpersonID"));
			 pstmt.execute();
			

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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>"+"Succesfully Inserted Training Id: "+" "+request.getParameter("TrainingID")+"</h1>");
		System.out.println("Succesfully Inserted");
	}

	@Override
	public void init() throws ServletException {
		URL = "jdbc:oracle:thin:@localhost:1521:XE";
		USER = "system";
		PASS = "Chiku1._";
		super.init();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
