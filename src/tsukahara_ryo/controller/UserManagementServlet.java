package tsukahara_ryo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tsukahara_ryo.beans.Branch;
import tsukahara_ryo.beans.Position;
import tsukahara_ryo.beans.User;
import tsukahara_ryo.service.UserService;

@WebServlet(urlPatterns = { "/usermanagement" })
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> users = new UserService().getAllUser();
		request.setAttribute("users", users);

		List<Branch> branches = new UserService().getBranch();
		request.setAttribute("branches", branches);

		List<Position> positions = new UserService().getPosition();
		request.setAttribute("positions", positions);


		request.getRequestDispatcher("/usermanagement.jsp").forward(request, response);
	}

}
