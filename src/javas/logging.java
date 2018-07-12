package javas;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class logging
 */
@WebFilter("/logging")
public class logging implements Filter {

	/**
	 * Default constructor.
	 */
	public logging() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Filter is destroyed..");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		final HttpServletRequest req = (HttpServletRequest) request;

		final String remote = req.getRemoteAddr();
		final StringBuffer reqUrl = req.getRequestURL();
		System.out.println(
				"Logging : IP adress " + remote + " , Time " + new Date().toString() + " , acces to " + reqUrl);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		final String filterParam = fConfig.getInitParameter("param");

		System.out.println(filterParam);
	}

}
