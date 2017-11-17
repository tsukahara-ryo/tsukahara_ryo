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

import org.apache.commons.lang.StringUtils;

import tsukahara_ryo.beans.Branch;
import tsukahara_ryo.beans.Position;
import tsukahara_ryo.beans.User;
import tsukahara_ryo.service.BranchService;
import tsukahara_ryo.service.PositionService;
import tsukahara_ryo.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branches = new BranchService().getBranch();
		List<Position> position = new PositionService().getPosition();
		request.setAttribute("branches", branches);
		request.setAttribute("positions", position);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
				List<String> messages = new ArrayList<String>();
				HttpSession session = request.getSession();
				User user = new User();
				user.setLogin_id(request.getParameter("login_id"));
				user.setPassword(request.getParameter("password"));
				user.setName(request.getParameter("name"));
				user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
				user.setPosition_id(Integer.parseInt(request.getParameter("position_id")));

				if (isValid(request, messages) == true) {
					new UserService().register(user);
					response.sendRedirect("./");//仮実装
				} else {
					session.setAttribute("errorMessages", messages);
					request.setAttribute("editUser", user);
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
			}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String chack =  new UserService().chackId("login_id");
		String password2 = request.getParameter("password2");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (!password.matches("[0-9a-zA-Z]{6,20}")){
			messages.add("6文字以上20文字以下で半角文字");
		}
		if (chack != null){
			messages.add("このユーザーIDは使用されています");
		}
		if (password == password2){
			messages.add("パスワードが一致しません");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
