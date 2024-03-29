package Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = { "" })
public class AuthFilter implements Filter {

	private FilterConfig filterConfig = null;

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		boolean isLoginPathDoingRequest = httpRequest.getRequestURI()
				.toString().toLowerCase().contains("login");

		HttpSession session = httpRequest.getSession();
		Boolean loggedIn = (Boolean) session.getAttribute("isLoggedIn");

		if (isLoginPathDoingRequest == true) {

			chain.doFilter(request, response);
			return;

		} else if (loggedIn != null) {

			chain.doFilter(request, response);
			return;

		} else {
			request.getServletContext()
					.getRequestDispatcher("/WEB-INF/login.jsp")
					.forward(request, response);

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		this.filterConfig = fConfig;

	}

}
