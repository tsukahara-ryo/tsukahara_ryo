package tsukahara_ryo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				User user = new User();
				user.setLogin_id(request.getParameter("login_id"));
				user.setPassword(request.getParameter("password"));
				user.setName(request.getParameter("name"));
				user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
				user.setPosition_id(Integer.parseInt(request.getParameter("position_id")));

				if (isValid(request, messages) == true) {
					new UserService().register(user);
					response.sendRedirect("usermanagement");//仮実装
				} else {
					request.setAttribute("errorMessages", messages);
					request.setAttribute("editUser", user);
					List<Branch> branches = new BranchService().getBranch();
					List<Position> position = new PositionService().getPosition();
					request.setAttribute("branches", branches);
					request.setAttribute("positions", position);

					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
			}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		int branch = (Integer.parseInt(request.getParameter("branch_id")));
		int position = (Integer.parseInt(request.getParameter("position_id")));


		if (StringUtils.isBlank(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isBlank(password) == true) {
			messages.add("パスワードを入力してください");
		}
		boolean isUnique = new UserService().signUpIsUnique(loginId);
		if (!isUnique){
			messages.add("このログインIDは使用されています");
		}
		if (!password.matches("[0-9a-zA-Z]{6,20}")){
			messages.add("パスワードは6文字以上20文字以下で半角文字で入力してください");
		}
		if (!password.equals(password2)){
			messages.add("パスワードが一致しません");
		}
		if (StringUtils.isBlank(name) == true) {
			messages.add("名前を入力してください");
		}
		if (branch == 1 && position == 3){
			messages.add("店名と部署・役職が不正な組み合わせです");
		}
		if (branch == 1 && position == 4){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		if (branch == 2 && position == 1){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		if (branch == 2 && position == 2){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		if (branch == 3 && position == 1){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		if (branch == 3 && position == 2){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		if (branch == 4 && position == 1){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		if (branch == 4 && position == 2){
			messages.add("店名と部署・役職が不正な組み合わせです");

		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
