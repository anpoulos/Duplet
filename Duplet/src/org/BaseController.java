package org;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaunt.JauntException;
import com.jaunt.UserAgent;



/**
 * Servlet implementation class BaseController
 */
@WebServlet("/Home")
public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseController() {
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
    
    out.println("<script type='text/javascript'>");
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/js/functions.js");
    dispatcher.include(request, response);
    out.println("</script>");
    
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/html/Home.html");
    view.include(request, response);
    
    out.println("<p>From Java environment: Test</p>");
    out.println("<form action='Home' method='post'>");
    out.println("Website name: <input type='text' name='searchterm' placeholder='e.g. google.com'>");
    out.println("<input type='submit' value='submit'>");
    out.println("</form>");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<h1>Through Java environment:</h1>");
    
		String searchterm = request.getParameter("searchterm");
		
		try{
		  UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
		  userAgent.visit("http://"+searchterm);                        //visit a url  
		  out.println(userAgent.doc.innerHTML());               //print the document as HTML
		}
		catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
		  System.err.println(e);
		}
		
	}

}
