package javas;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		if (password.equals(repassword)) {
			DbManager db = new DbManager();
			boolean signup = db.userSignup(username, password);

			if (signup == true) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("basket", "0");
				session.setAttribute("statu", "login");
				RequestDispatcher rd = request.getRequestDispatcher("index");
				rd.include(request, response);
			} else {
				out.println("<script>\r\n" + "window.alert(\"someting wrong\");\r\n" + "</script>");
				RequestDispatcher rd = request.getRequestDispatcher("/register.html");
				rd.include(request, response);
			}
		} else {
			out.println("<script>\r\n" + "window.alert(\"passwords doesn't match\");\r\n" + "</script>");

			RequestDispatcher rd = request.getRequestDispatcher("/register.html");
			rd.include(request, response);
		}
	}

}
