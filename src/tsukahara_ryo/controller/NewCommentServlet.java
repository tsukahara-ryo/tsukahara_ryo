//package tsukahara_ryo.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.lang.StringUtils;
//
//import tsukahara_ryo.beans.Comment;
//import tsukahara_ryo.beans.User;
//import tsukahara_ryo.service.MessageService;
//
//@WebServlet(urlPatterns = { "/newComment" })
//public class NewCommentServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//
//
//	@Override
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws IOException, ServletException {
//
//		List<String> comments = new ArrayList<String>();
//
//
//
//		HttpSession session = request.getSession();
//
//		User user = (User) request.getSession().getAttribute("loginUser");
//
//		System.out.println(user.getId());
//		Comment comment = new Comment();
////		comment.setId(Integer.parseInt(request.getParameter("branch_id")));
//		comment.setText(request.getParameter("text"));
//		comment.setMessage_id(Integer.parseInt(request.getParameter("message_id")));
//		comment.setUser_id(user.getId());
//
//		if (isValid(request, comments) == true) {
//
//
//
//			new MessageService().register(comment);
//
//			response.sendRedirect("./");//仮実装
//		} else {
//			session.setAttribute("errorMessages", comment);
//			request.setAttribute("newComment", comment);
//			request.getRequestDispatcher("top.jsp").forward(request, response);
//		}
//	}
//
//
//	private boolean isValid(HttpServletRequest request, List<String> messages) {
//
//		String message = request.getParameter("text");
//
//		if (StringUtils.isEmpty(message) == true) {
//			messages.add("メッセージを入力してください");
//		}
//		if (1000 < message.length()) {
//			messages.add("1000文字以下で入力してください");
//		}
//		if (messages.size() == 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//}
