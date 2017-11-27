package tsukahara_ryo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tsukahara_ryo.beans.User;

@WebFilter("/*")

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest) req).getRequestURI().endsWith("/login") || ((HttpServletRequest) req).getRequestURI().endsWith("/style.css")) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = ((HttpServletRequest) req).getSession();
		List<String> messages = new ArrayList<String>();

		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			chain.doFilter(req, res);
			return;
		}
		messages.add("ログインしてください");
		session.setAttribute("errorMessages", messages);
		((HttpServletResponse) res).sendRedirect("login");

	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}
