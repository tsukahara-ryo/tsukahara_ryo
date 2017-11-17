package tsukahara_ryo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tsukahara_ryo.beans.User;
import tsukahara_ryo.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String is_deleted = request.getParameter("is_deleted");

		LoginService loginService = new LoginService();
		User user = loginService.login(login_id, password, is_deleted);

		HttpSession session = request.getSession();
		if (user != null) {

			session.setAttribute("loginUser", user);
			session.removeAttribute("user");
			response.sendRedirect("./");
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			request.setAttribute("errorMessages", messages);
			response.sendRedirect("login");
		}
	}

}
