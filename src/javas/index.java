package javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbManager db = new DbManager();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public index() {
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

		PrintWriter out = response.getWriter();

		/*
		 * HttpSession session = request.getSession(true); Date createTime = new
		 * Date(session.getCreationTime()); Date lastAccessTime = new
		 * Date(session.getLastAccessedTime());
		 * 
		 * Integer visitCount = new Integer(0); String visitCountKey = new
		 * String("visitCount");
		 * 
		 * String username=request.getParameter("username"); String
		 * password=request.getParameter("password"); if (session.isNew()) {
		 * session.setAttribute(username, password); } else { visitCount =
		 * (Integer)session.getAttribute(visitCountKey); visitCount = visitCount + 1; }
		 * session.setAttribute(visitCountKey, visitCount); RequestDispatcher
		 * rd=request.getRequestDispatcher("/index.html"); rd.include(request,response);
		 */
		String statu = request.getParameter("statu");
		String bookid = request.getParameter("bookid");
		try {
			HttpSession session = request.getSession();
			if (session != null) {
				String name = (String) session.getAttribute("username");
				if(statu.equals("buy"))
				{
					if (Integer.parseInt((String) session.getAttribute("basket")) < 4) {
						int basket = 0;
						basket = Integer.parseInt((String) session.getAttribute("basket")) + 1;
						session.setAttribute("basket", String.valueOf(basket));
						int userid = (int) session.getAttribute("userid");
						db.basketadd(userid, Integer.parseInt(bookid));
					} else {
						out.println("<script>\r\n" + "window.alert(\"Basket max size : 4 \");\r\n" + "</script>");
					}
				}

				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.include(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
