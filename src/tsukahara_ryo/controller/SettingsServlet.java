package tsukahara_ryo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import tsukahara_ryo.beans.Branch;
import tsukahara_ryo.beans.Position;
import tsukahara_ryo.beans.User;
import tsukahara_ryo.exception.NoRowsUpdatedRuntimeException;
import tsukahara_ryo.service.BranchService;
import tsukahara_ryo.service.PositionService;
import tsukahara_ryo.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
@MultipartConfig(maxFileSize = 100000)
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branches = new BranchService().getBranch();
		List<Position> position = new PositionService().getPosition();
		request.setAttribute("branches", branches);
		request.setAttribute("positions", position);

		User editUser = new UserService().getUser(Integer.parseInt(request.getParameter("id")));

		request.setAttribute("editUser", editUser);

		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User editUser = getEditUser(request);

		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("settings");
			}
			response.sendRedirect("usermanagement");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", editUser);
			response.sendRedirect("settings");
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		User editUser = new User();

		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setLogin_id(request.getParameter("login_id"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		editUser.setPosition_id(Integer.parseInt(request.getParameter("position_id")));
		return editUser;
	}



	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String account = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (password == password2){
			messages.add("パスワードが一致しません");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}


