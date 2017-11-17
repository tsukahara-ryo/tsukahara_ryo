package tsukahara_ryo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import tsukahara_ryo.beans.UserComment;
import tsukahara_ryo.beans.UserMessage;
import tsukahara_ryo.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String cotegory = request.getParameter("cotegory");

		String begin = getParameter(request, "begin", "2017-11-01");
		String end = getParameter(request, "end", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		List<UserComment> comment = new MessageService().getUserComment();
		List<UserMessage> messages = new MessageService().getMessage(begin, end, cotegory);

		request.setAttribute("comments", comment);
		request.setAttribute("messages", messages);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

	private static String getParameter(HttpServletRequest req, String key, String defaultValue) {
		return StringUtils.isEmpty(req.getParameter(key)) ? defaultValue : req.getParameter(key);
	}

}


