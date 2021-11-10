package mainServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int visitCount;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		visitCount = 0;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		visitCount++;
		String userName = request.getParameter("userName");
		response.getWriter().append("<!DOCTYPE html>\r\n" +
				"<html>\r\n" +
				"<head>\r\n" +
				"<meta charset=\"ISO-8859-1\">\r\n" +
				"<title>A page served by Servlet!</title>\r\n" +
				"</head>\r\n" +
				"<body>\r\n");
		if (userName!=null) {
			response.getWriter().append("Hi "+userName +"<br>\r\n");
			
			response.getWriter().append("What would you like to do today?<br><br>\r\n"+
					"</body>\r\n" +
					"</html>");
			
		}
		response.getWriter().append("Page Visited: "+visitCount+"times<br><br>\r\n" +
				"<a href=\"/project3/index.html\">Goto Homepage</a><br>\r\n" +
				"</body>\r\n" +
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}