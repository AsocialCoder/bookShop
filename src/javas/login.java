package javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
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

		DbManager db = new DbManager();
		boolean login = db.userlogin(username, password);

		if (login == true) {
			HttpSession session = request.getSession();
			session.setAttribute("userid",db.getUserID());
			session.setAttribute("username", username);
			session.setAttribute("basket", "0");
			//session.setAttribute("statu", "login");
			db.clearbasket(db.getUserID());
			RequestDispatcher rd = request.getRequestDispatcher("index?statu=login");
			rd.include(request, response);
		} else {
			out.println("<script>\r\n" + "window.alert(\"username or password incorret\");\r\n" + "</script>");
			RequestDispatcher rd = request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		}

	}

}
