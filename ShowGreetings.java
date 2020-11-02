package com.csg.servlet.assig1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowGreetings
 */
@WebServlet("/ShowGreetings")
public class ShowGreetings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGreetings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
		String greetings="";
		if(timeOfDay >= 0 && timeOfDay < 12){
		   greetings="Good Morning";        
		}else if(timeOfDay >= 12 && timeOfDay < 16){
			greetings="Good Afternoon";
		}else if(timeOfDay >= 16 && timeOfDay < 21){
			greetings="Good Evening";
		}else if(timeOfDay >= 21 && timeOfDay < 24){
			greetings="Good Night";
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>"+greetings+" "+request.getParameter("name")+"</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
