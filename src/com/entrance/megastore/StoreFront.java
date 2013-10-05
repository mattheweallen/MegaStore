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

//TODO properly indent code, refer to email from Dr Hunt.

/**
 * Servlet implementation class StoreFront
 */
@WebServlet(description = "This is a Store Front Servlet", urlPatterns = { "/entrance" })
public class StoreFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private final static String defaultImage =
	// "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg";
	// private final static String cyclocrossImage =
	// "http://upload.wikimedia.org/wikipedia/en/thumb/7/72/Cyclocross_runup.JPG/700px-Cyclocross_runup.JPG";
	// private final static String mountainImage =
	// "http://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/XC_MTB_2_Stevage.jpg/800px-XC_MTB_2_Stevage.jpg";
	// private final static String trackImage =
	// "http://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Manchester_Velodrome_2011.jpg/800px-Manchester_Velodrome_2011.jpg";
	// private final static String roadImage =
	// "http://upload.wikimedia.org/wikipedia/commons/thumb/4/41/TourDeFrance_2005-07-08.JPG/800px-TourDeFrance_2005-07-08.JPG";
	// private final static String bmxImage =
	// "http://upload.wikimedia.org/wikipedia/commons/f/fe/BMX_racing_action_photo.jpg";

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
	private Map<String, String> favoriteMap;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreFront() {
		// favoriteMap = new HashMap<String, String>();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		// favoriteMap.put("currentFavorite",
		// "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg");
		// favoriteMap.put("olderFavorite", null);
		// favoriteMap.put("oldestFavorite", null);
		
//		response.addCookie(new Cookie("firstFavorite", "blargitykfjsdljfds"));
//		response.addCookie(new Cookie("2Favorite", "cccccccblargitykfjsdljfds"));
//		response.addCookie(new Cookie("3Favorite", "hhhhhhhhhhhhhhhhhhhhhhcccccccblargitykfjsdljfds"));
//		
//		
//		System.out.println("1: " + getCookieValue(request.getCookies(), "firstFavorite", null));
//		System.out.println("2: " + getCookieValue(request.getCookies(), "2Favorite", null));
//		System.out.println("3: " + getCookieValue(request.getCookies(), "3Favorite", null));
//		
//
		PrintWriter out = response.getWriter();
		session = request.getSession(true);
		sessionId = session.getId();
		
		updateTheme(request);
		
		
		String firstFavorite = "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg";
		if(request.getParameter("race_type_select") != null) {
			firstFavorite = imageMap.get(request.getParameter("race_type_select"));
			response.addCookie(new Cookie("firstFavorite", firstFavorite));
		}
		
		Map<String, String> cookiesMap =  readCookies(request);
		String secondFavorite = cookiesMap.get("firstFavorite");
		response.addCookie(new Cookie("secondFavorite", secondFavorite));
		
		String thirdFavorite = cookiesMap.get("secondFavorite");
		response.addCookie(new Cookie("thirdFavorite", thirdFavorite));
		
//		//boolean isSessionNew = session.isNew();
//		
//		String firstFavorite = getCookieValue(request.getCookies(), "firstFavorite", null);
//		//System.out.println("firstFavorite "  + firstFavorite);
//		String secondFavorite = getCookieValue(request.getCookies(), "secondFavorite", null);
//		//System.out.println("secondFavorite "  + secondFavorite);
//		String thirdFavorite  = getCookieValue(request.getCookies(), "thirdFavorite", null);
//		//System.out.println("thirdFavorite "  + thirdFavorite);
//		
//		//System.out.println("cookieValue1 "  + thirdFavorite);
		
		
//		if(!doesCookieExist(request.getCookies(), "firstFavorite")) {
//			firstFavorite = "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg";
//			response.addCookie(new Cookie("firstFavorite", null));
//		} else if (firstFavorite == null && request.getParameter("race_type_select") == null) {
//			firstFavorite = "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg";
//		} else if (firstFavorite == null && request.getParameter("race_type_select") != null) {
//			firstFavorite = imageMap.get(request.getParameter("race_type_select"));
//			setCookieValue(request.getCookies(), "firstFavorite", firstFavorite);
//		} else if (firstFavorite != null && request.getParameter("race_type_select") != null) {
//			if(!doesCookieExist(request.getCookies(), "secondFavorite")) {
//				secondFavorite = firstFavorite;
//				response.addCookie(new Cookie("secondFavorite", firstFavorite));
//			} else {
//				if(!doesCookieExist(request.getCookies(), "thirdFavorite")) {
//					response.addCookie(new Cookie("thirdFavorite", secondFavorite));
//					thirdFavorite = secondFavorite;
//				} else {
//					setCookieValue(request.getCookies(), "thirdFavorite", secondFavorite);
//					thirdFavorite = secondFavorite;
//				}
//				setCookieValue(request.getCookies(), "secondFavorite", firstFavorite);
//				secondFavorite = firstFavorite;
//			}
//			firstFavorite = imageMap.get(request.getParameter("race_type_select"));
//			setCookieValue(request.getCookies(), "firstFavorite", firstFavorite);
//			
//		}
		
		
		

		// for(int i = 0; i < request.getCookies().length; i++) {

		// }

		//if (isSessionNew) {
			//initializeSession(request, response);
			//System.out.println("Start Session");
		//} else {
			//updateSession(request, response);
			//System.out.println("Same Session");
		//}

		// String cookieFromRequestHeader = request.getHeader("cookie");

		// if(!isSessionNew) {

		// }

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
			// out.println(".li {display:inline}");
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
				if(request.getParameter("race_type_select") != null && request.getParameter("race_type_select").equals(s)) {
					out.println("<option selected value=\"" + s + "\">" + cyclingMap.get(s)
							+ "</option>");
				} else {
					out.println("<option value=\"" + s + "\">" + cyclingMap.get(s)
							+ "</option>");
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
			out.println("value=\"radB2\"/>");
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

			// if(isSessionNew) {
			// out.println("<td>");
			// out.println("<img src=\"" + defaultImage +
			// "\" alt=\"Leon Georget\" width=\"30px\" height=\"30px\">");
			// out.println("</td>");
			// } else {
			
			out.println("<td>");
			out.println("<table>");
			out.println("<tr>");
			
			out.println("<td>");
			out.println("<img src=\"" + firstFavorite
					+ "\" width=\"30px\" height=\"30px\">");
//			out.println("</td>");
			if (secondFavorite != null) {
				//out.println("<td>");
				out.println("<img src=\"" + secondFavorite
						+ "\" width=\"30px\" height=\"30px\">");
				//out.println("</td>");
			}
			if (thirdFavorite != null) {
				//out.println("<td>");
				out.println("<img src=\"" + thirdFavorite
						+ "\" width=\"30px\" height=\"30px\">");
				//out.println("</td>");
			}
			out.println("</td>");
			// }
			
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
		//System.out.println("Matthew I am in the get"
			//	+ request.getParameter("race_type_select") + " "
				//+ request.getParameter("theme"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Matthew I am in the post");
	}

//	private void initializeSession(HttpServletRequest request,
//			HttpServletResponse response) {
//		//favoriteMap = new HashMap<String, String>();
//		//favoriteMap
//			//	.put("currentFavorite",
//				//		"http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg");
//		// favoriteMap.put("olderFavorite", null);
//		// favoriteMap.put("oldestFavorite", null);
//
//		response.addCookie(new Cookie("currentFavorite", "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Leon_Georget_1909.jpg/785px-Leon_Georget_1909.jpg"));
//
//		//background1 = "radB1";
//		//background2 = "radB2";
//		//session.setAttribute("favoriteMap", favoriteMap);
//	}

//	@SuppressWarnings("unchecked")
//	private void updateSession(HttpServletRequest request,
//			HttpServletResponse response) {
//		favoriteMap = (HashMap<String, String>) session
//				.getAttribute("favoriteMap");
//		updateTheme(request, response);
//		updateFavoriteMap(request, response);
//		session.setAttribute("favoriteMap", favoriteMap);
//	}

	private void updateTheme(HttpServletRequest request) {
		if (!"gnarly".equals(request.getParameter("theme"))) {
			background1 = "radB1";
			background2 = "radB2";
		} else {
			background1 = "gnarlyB1";
			background2 = "gnarlyB2";
		}
	}
	
	private void writeCookies(HttpServletResponse response, Map<String, String> cookiesToAdd) {
		
	}
	
	private Map<String, String> readCookies(HttpServletRequest request) {
		Map<String, String> cookies = new HashMap<String, String>();
		Cookie[] c = request.getCookies();
		if(c != null) {
			for(int i = 0; i < c.length; i++) {
				cookies.put(c[i].getName(), c[i].getValue());
				System.out.println(c[i].getName() + " " +  c[i].getValue());
			}
		}
		return cookies;
	}

//	private void updateFavoriteMap(HttpServletRequest request,
//			HttpServletResponse response) {
//		Map<String, String> tempMap = new HashMap<String, String>();
//		for (int i = 0; i < request.getCookies().length; i++) {
//			if ("currentFavorite".equals(request.getCookies()[i].getName())) {
//				tempMap.put("olderFavorite", request.getCookies()[i].getValue());
//				request.getCookies()[i].setValue(request
//						.getParameter("race_type_select"));
//				favoriteMap.put("currentFavorite",
//						imageMap.get(request.getCookies()[i].getValue()));
//				//System.out.println("In branch first for first if");
//				//System.out.println(favoriteMap.get("currentFavorite"));
//			}
//			if ("olderFavorite".equals(request.getCookies()[i].getName())) {
//				tempMap.put("oldestFavorite",
//						request.getCookies()[i].getValue());
//				//System.out.println("In branch first for second if");
//			}
//		}
//
//		for (int i = 0; i < request.getCookies().length; i++) {
//			if ("olderFavorite".equals(request.getCookies()[i].getName())) {
//				request.getCookies()[i].setValue(tempMap.get("olderFavorite"));
//				favoriteMap.put("olderFavorite",
//						imageMap.get(request.getCookies()[i].getValue()));
//				//System.out.println("In branch second for first if");
//				//System.out.println(favoriteMap.get("olderFavorite"));
//			}
//			if ("oldestFavorite".equals(request.getCookies()[i].getName())) {
//				request.getCookies()[i].setValue(tempMap.get("oldestFavorite"));
//				favoriteMap.put("oldestFavorite",
//						imageMap.get(request.getCookies()[i].getValue()));
//				//System.out.println("In branch second for second if");
//				//System.out.println(favoriteMap.get("oldestFavorite"));
//			}
//
//		}
//
//		for (String s : favoriteMap.keySet()) {
//			// System.out.println(favoriteMap.get(s));
//		}
//	}

//	public String getCookieValue(Cookie[] cookies, String cookieName,
//			String defaultValue) {
//		for (int i = 0; i < cookies.length; i++) {
//			Cookie cookie = cookies[i];
//			if (cookieName.equals(cookie.getName()))
//				return (cookie.getValue());
//		}
//		return (defaultValue);
//	}
	
//	public void setCookieValue(Cookie[] cookies, String cookieName,
//			String cookieValue) {
//		for (int i = 0; i < cookies.length; i++) {
//			if (cookieName.equals(cookies[i].getName()))
//				cookies[i].setValue(cookieValue);
//		}
//	}
//	
//	public boolean doesCookieExist(Cookie[] cookies, String cookieName) {
//		for (int i = 0; i < cookies.length; i++) {
//			Cookie cookie = cookies[i];
//			if (cookieName.equals(cookie.getName()))
//				return true;
//		}
//		return false;
//	}
}
