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

import tsukahara_ryo.beans.Message;
import tsukahara_ryo.beans.User;
import tsukahara_ryo.service.MessageService;

@WebServlet(urlPatterns = { "/newmessage" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		request.getRequestDispatcher("newmassage.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();



		HttpSession session = request.getSession();
		User user = (User) request.getSession().getAttribute("loginUser");

		Message message = new Message();
		message.setSubject(request.getParameter("subject"));
		message.setText(request.getParameter("text"));
		message.setCotegory(request.getParameter("cotegory"));
		message.setUser_id(user.getId());

		if (isValid(request, messages) == true) {
			new MessageService().register(message);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", message);
			//			request.getRequestDispatcher("newmessage.jsp").forward(request, response);
			response.sendRedirect("newmessage");
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String message = request.getParameter("text");
		String subject = request.getParameter("subject");
		String cotegory = request.getParameter("cotegory");



		if(StringUtils.isEmpty(subject) || subject.equals("") == true) {
			messages.add("件名を入力してください");
		}
		if (30 < subject.length()) {
			messages.add("件名は30文字以下で入力してください");
		}
		if(StringUtils.isEmpty(cotegory) || cotegory.equals("") == true){
			messages.add("カテゴリーを入力してください");
		}
		if(10 < cotegory.length()){
			messages.add("カテゴリーは10文字以下で入力してください");
		}

		if (StringUtils.isEmpty(message) || message.equals("") == true) {
			messages.add("本文を入力してください");
		}
		if (1000 < message.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
