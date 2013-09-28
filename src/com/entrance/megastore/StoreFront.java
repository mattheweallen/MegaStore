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
		
		try {
			out.println("<html>"); 
			out.println("<head>"); 
			out.println("<title>Cycling</title>");
            out.println("<style>table{border:1px solid black;}</style>"); 
            out.println("</head>");  
            out.println("<body>");
            out.println("<form>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>");
            out.println("Matt's World of Cycling");
            out.println("</th>");
            out.println("<td>");
            out.println("<select>");
            for(String s : cyclingMap.keySet()) {
            	out.println("<option value=\"" + s + "\">" + cyclingMap.get(s) + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"submit\" value=\"Submit\"></input>");
            out.println("</td>");
            out.println("</th>");
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
