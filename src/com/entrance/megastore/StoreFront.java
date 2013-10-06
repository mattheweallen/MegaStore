package com.entrance.megastore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StoreFront
 * 
 * @author matthew
 * 
 */
@WebServlet(description = "This is a Store Front Servlet", urlPatterns = { "/entrance" })
public class StoreFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Map<String, String> imageMap;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put(
				"cx",
				"http://upload.wikimedia.org/wikipedia/en/thumb/7/72/Cyclocross_runup.JPG/700px-Cyclocross_runup.JPG");
		aMap.put(
				"mtb",
				"http://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/XC_MTB_2_Stevage.jpg/800px-XC_MTB_2_Stevage.jpg");
		aMap.put(
				"bmx",
				"http://upload.wikimedia.org/wikipedia/commons/f/fe/BMX_racing_action_photo.jpg");
		aMap.put(
				"road",
				"http://upload.wikimedia.org/wikipedia/commons/thumb/4/41/TourDeFrance_2005-07-08.JPG/800px-TourDeFrance_2005-07-08.JPG");
		aMap.put(
				"track",
				"http://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Manchester_Velodrome_2011.jpg/800px-Manchester_Velodrome_2011.jpg");
		imageMap = Collections.unmodifiableMap(aMap);
	}

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

	private String background1;
	private String background2;
	private String sessionId;
	private HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreFront() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		session = request.getSession(true);
		sessionId = session.getId();

		updateTheme(request);

		String firstFavorite = "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg";
		if (request.getParameter("race_type_select") != null) {
			firstFavorite = imageMap.get(request
					.getParameter("race_type_select"));
			response.addCookie(new Cookie("firstFavorite", firstFavorite));
		}
		String secondFavorite = null;
		String thirdFavorite = null;
		if (!session.isNew()) {
			Map<String, String> cookiesMap = readCookies(request);
			secondFavorite = cookiesMap.get("firstFavorite");
			response.addCookie(new Cookie("secondFavorite", secondFavorite));

			thirdFavorite = cookiesMap.get("secondFavorite");
			response.addCookie(new Cookie("thirdFavorite", thirdFavorite));
		}

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
			out.println("<table width=\"100%\"><tr><td>My Favorite Racing is</td></tr><tr><td><select name=\"race_type_select\">");

			for (String s : cyclingMap.keySet()) {
				if (request.getParameter("race_type_select") != null
						&& request.getParameter("race_type_select").equals(s)) {
					out.println("<option selected value=\"" + s + "\">"
							+ cyclingMap.get(s) + "</option>");
				} else {
					out.println("<option value=\"" + s + "\">"
							+ cyclingMap.get(s) + "</option>");
				}
			}
			out.println("</select></td></tr></table>");
			out.println("</td>");
			out.println("<td style=\"width:10%\">");
			out.println("<input type=\"submit\" value=\"Submit\" name=\"favoriteSubmit\">");
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

			if (!"gnarly".equals(request.getParameter("theme"))) {
				out.println("<input type=\"radio\" name=\"theme\" value=\"rad\" checked=\""
						+ "checked" + "\"  />");
				out.println("Jens");
				out.println("<input type=\"radio\" value=\"gnarly\" name=\"theme\"");
			} else {
				out.println("<input type=\"radio\" name=\"theme\" value=\"rad\"/>");
				out.println("Jens");
				out.println("<input type=\"radio\" name=\"theme\" value=\"gnarly\" checked=\"checked\"");
			}
			out.println("/>");
			out.println("Fabian");
			out.println("</li>");
			out.println("</ul>");
			out.println("</td>");

			out.println("<td>");
			out.println("<input type=\"submit\" value=\"update\" name=\"themeSubmit\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");

			out.println("</td>");
			out.println("</tr>");
			out.println("<tr align=\"left\">");

			out.println("<td>");
			out.println("<table>");
			out.println("<tr>");

			out.println("<td>");

			if (thirdFavorite != null && !"".equals(thirdFavorite.trim())) {
				out.println("<img src=\"" + thirdFavorite
						+ "\" width=\"50px\" height=\"50px\" alt=\"Cycling\">");
			}

			if (secondFavorite != null && !"".equals(secondFavorite.trim())) {
				out.println("<img src=\"" + secondFavorite
						+ "\" width=\"50px\" height=\"50px\" alt=\"Cycling\">");
			}

			out.println("<img src=\"" + firstFavorite
					+ "\" width=\"50px\" height=\"50px\" alt=\"Cycling\">");

			out.println("</td>");

			out.println("</tr>");
			out.println("</table>");
			out.println("</td>");

			out.println("</tr>");

			out.println("</table>");

			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @param request
	 */
	private void updateTheme(HttpServletRequest request) {
		if (!"gnarly".equals(request.getParameter("theme"))) {
			background1 = "radB1";
			background2 = "radB2";
		} else {
			background1 = "gnarlyB1";
			background2 = "gnarlyB2";
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private Map<String, String> readCookies(HttpServletRequest request) {
		Map<String, String> cookies = new HashMap<String, String>();
		Cookie[] c = request.getCookies();
		if (c != null) {
			for (int i = 0; i < c.length; i++) {
				cookies.put(c[i].getName(), c[i].getValue());
			}
		}
		return cookies;
	}
}
