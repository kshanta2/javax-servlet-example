package com.csg.servlet.assig1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateRowDynamically
 */
@WebServlet("/CreateRowDynamically")
public class CreateRowDynamically extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRowDynamically() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    out.println("<h1>" + "Hello printing table using for loop" + "</h1>");
	    out.println("<table>");
	    out.println("<thead><tr><td>Column1</td><td>Column2</td><td>Column3</td></tr></thead>");
	    out.println("<tbody>");
	    for(int i=0;i<25;i++) {
	    	out.println("<tr>");
	    	for(int j=1;j<=3;j++) {
	    		out.println("<td>"+"Row"+(i+1)+", Col"+j+"</td>");
	    	}
	    	out.println("</tr>");
	    }
	    out.println("</tbody></table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
