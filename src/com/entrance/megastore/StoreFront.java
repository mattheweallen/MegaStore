package com.entrance.megastore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//TODO properly indent code, refer to email from Dr Hunt.

/**
 * Servlet implementation class StoreFront
 */
@WebServlet(description = "This is a Store Front Servlet", urlPatterns = { "/entrance" })
public class StoreFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Map<String, String> cyclingMap;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("cx", "Cyclocross");
        aMap.put("mtb", "Mountain");
        aMap.put("bmx", "Bicycle Motocross");
        aMap.put("road", "Road");
        aMap.put("track", "Track");
        cyclingMap = Collections.unmodifiableMap(aMap);
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreFront() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String background1 = "radB1";
		String background2 = "radB2";
		
		final String defaultImage = "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg";
		
		HttpSession session = request.getSession(true);
		//boolean isSessionNew = session.isNew();
		String sessionId = session.getId();
		//String cookieFromRequestHeader = request.getHeader("cookie");
		
		
		try {
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
			out.println("<html>"); 
			out.println("<head>"); 
			out.println("<title>Cycling</title>");
            out.println("<style type=\"text/css\">");
            out.println(".radB1{background-color:#F0F0F0;}");
            out.println(".radB2{background-color:#d0e4fe;}");
            out.println(".gnarlyB1{background-color:#66FFFF;}");
            out.println(".gnarlyB2{background-color:#66FF00;}");
            //out.println(".li {display:inline}");
            out.println("</style>");
            out.println("</head>");  
            out.println("<body>");
            out.println("<form action=\"entrance\">");
            out.println("<table style=\"border:1px solid black\" width=\"100%\">");
            out.println("<tr>");
            out.println("<td>");
            out.println("<table class=\"" + background1 + "\" width=\"100%\">");
            out.println("<tr>");
            out.println("<td style=\"width:70%\">");
            out.println("<h1>Matt's World of Cycling</h1>");
            out.println("</td>");
            out.println("<td style=\"width:20%\">");
            out.println("<table width=\"100%\"><tr><td>My Favorite Racing is</td></tr><tr><td><select>");
            
            for(String s : cyclingMap.keySet()) {
            	out.println("<option value=\"" + s + "\">" + cyclingMap.get(s) + "</option>");
            }
            out.println("</select></td></tr></table>");
            out.println("</td>");
            out.println("<td style=\"width:10%\">");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<table class=\"" + background2 + "\" width=\"100%\">");
            out.println("<tr>");
            out.println("<td>");
            out.println("<p>");
            out.println("Cycling has many discplines. ");
            out.println("A few of the disciplines are cyclocross, mountain, bicycle motocross, road, and track. ");
            out.println("They are all related in that they are all done on a bicycle, but the bicycle as well as the strategy, and skills often differ. ");
            out.println("Cyclocross is raced in the fall and winter in some of the worst conditions. ");
            out.println("It is a half hour to an hour of delicious pain. ");
            out.println("The courses are usually a mix of surfaces: pavement, sand, grass, and dirt. ");
            out.println("Like mountain biking and bicycle motocross, the holeshot is important, and often determines who controls the race. ");
            out.println("The courses in mountain bike, bmx, and cyclocross races are often hard to pass on, so getting out front right away is crucial. ");
            out.println("Road cycling and track this is less important. Especially in road racing where the speeds are high enough that drafting is crucial to victory. ");
            out.println("Where as cyclocross, bmx, and mountain bike racing victory is often set up early in the race, track and road team tactics and racing strategy throughtout out the race are important to winning. ");
            out.println("That in a nut shell are some of the different disciplines in cycling. ");
            out.println("</p>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr align=\"right\">");
            out.println("<td>");
            out.println(sessionId);
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr align=\"right\">");
            out.println("<td>");
            
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<ul>");
            out.println("<li  style=\"list-style:none;\">");
            out.println("<input type=\"radio\" name=\"custom_type\" value=\"public\" />");
            out.println("Jens");
            out.println("<input type=\"radio\" name=\"custom_type\""); 
            out.println("value=\"private\" id=\"custom_venuetype_private\" />");
            out.println("Fabian");
            out.println("</li>");
            out.println("</ul>");
            out.println("</td>");
            
            out.println("<td>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr align=\"left\">");
            out.println("<td>");
            out.println("<img src=\"" + defaultImage + "\" alt=\"Leon Georget\" width=\"10%\" height=\"10%\">");
            out.println("</td>");
            
            
            
            out.println("</tr>");
            
            out.println("</table>");
            
            
            
            out.println("</form>");
            out.println("</body>");
            out.println("</html>"); 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
