package tsukahara_ryo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tsukahara_ryo.service.CommentService;


@WebServlet(urlPatterns = { "/comdel" })
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

				new CommentService().delComment(request.getParameter("id"));
				response.sendRedirect("./");



	}

}
