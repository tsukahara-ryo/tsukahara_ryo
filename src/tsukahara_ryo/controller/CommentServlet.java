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

import tsukahara_ryo.beans.Comment;
import tsukahara_ryo.beans.User;
import tsukahara_ryo.service.MessageService;

@WebServlet(urlPatterns = { "/newComment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> newcomments = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = (User) request.getSession().getAttribute("loginUser");

		Comment comment = new Comment();
		comment.setText(request.getParameter("comment"));
		comment.setUser_id(user.getId());
		comment.setMessage_id(Integer.parseInt(request.getParameter("message_id")));

		if (isValid(request, newcomments) == true) {
			new MessageService().register(comment);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", newcomments);
			request.setAttribute("editUser", comment);
			response.sendRedirect("./");
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> newcomments) {

		String comment = request.getParameter("comment");

		if (StringUtils.isBlank(comment) == true) {
			newcomments.add("コメントを入力してください");
		}

		if (500 < comment.length()) {
			newcomments.add("500文字以下で入力してください");
		}
		if (newcomments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
