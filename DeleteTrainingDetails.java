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
 * Servlet implementation class DeleteTrainingDetails
 */
@WebServlet("/DeleteTrainingDetails")
public class DeleteTrainingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String URL = null;
	String USER = null;
	String PASS = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTrainingDetails() {
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
		String resp="";
		resp+="<form action =\"DeleteTrainingDetails\" method = \"POST\">\r\n" + 
				"        TrainingID: <input type = \"text\" name = \"TrainingID\">\r\n" + 
				"        \r\n" + 
				"        \r\n" + 
				"        <input type = \"submit\" value = \"Delete Record\" />\r\n" + 
				"     </form> <br/><br/><br/>";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASS);
			String query = "select * from training_details ";
			PreparedStatement pstmt = conn.prepareStatement(query);
			resp+= "<table><thead><tr><td>TrainingID</td><td>TrainingName</td><td>Start Date</td>";
			resp+="<td>End Date</td><td>TrainingMode</td><td>BusinisessUnit</td><td>ContactpersonID</td></tr></thead>";
			resp+="<tbody>";
			 ResultSet rs = pstmt.executeQuery();
			 if(rs!=null) {
				 while(rs.next()) {
					 resp+="<tr>";
					 resp+="<td>"+rs.getString("TrainingID")+"</td>";
					 resp+="<td>"+rs.getString("TrainingName")+"</td>";
					 resp+="<td>"+rs.getString("Start_Date")+"</td>";
					 resp+="<td>"+rs.getString("EndDate")+"</td>";
					 resp+="<td>"+rs.getString("TrainingMode")+"</td>";
					 resp+="<td>"+rs.getString("BusinisessUnit")+"</td>";
					 resp+="<td>"+rs.getString("ContactpersonID")+"</td>";
						resp+="</tr>";
				 }
				 resp+="</tbody>";
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(resp);
		System.out.println("Succesfully Fetched");
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
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASS);
			String query = "delete from training_details where TrainingID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, request.getParameter("TrainingID"));
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
		out.println("<h1>Training Id:"+request.getParameter("TrainingID")+" deleted succesfully</h1>");
		
		System.out.println("deleted");
	}

}
