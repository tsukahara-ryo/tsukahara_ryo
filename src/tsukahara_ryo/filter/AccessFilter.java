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

@WebFilter("/usermanagement")

public class AccessFilter implements Filter{
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    		throws IOException,ServletException{


	    	HttpSession session = ((HttpServletRequest) req).getSession();
	    	List<String> messages = new ArrayList<String>();
			if (session == null){
				chain.doFilter(req, res);
				return;
			}

	    	User loginUser = (User) session.getAttribute("loginUser");
	        if(loginUser.getPosition_id() == 1){
	        	chain.doFilter(req, res);
	        	return;
	        }

	        messages.add("アクセス権限がありません");
			session.setAttribute("errorMessages", messages);
	    	((HttpServletResponse) res).sendRedirect("./");

	    }



	    public void init(FilterConfig config) throws ServletException{}
	    public void destroy(){}



}
