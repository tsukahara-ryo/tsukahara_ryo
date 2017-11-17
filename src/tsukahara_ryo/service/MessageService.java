package tsukahara_ryo.service;

import static tsukahara_ryo.utils.CloseableUtil.*;
import static tsukahara_ryo.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import tsukahara_ryo.beans.Comment;
import tsukahara_ryo.beans.Message;
import tsukahara_ryo.beans.UserComment;
import tsukahara_ryo.beans.UserMessage;
import tsukahara_ryo.dao.CommentDao;
import tsukahara_ryo.dao.MessageDao;
import tsukahara_ryo.dao.UserMessageDao;

public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, message);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



	public List<UserMessage> getMessage(String begin, String end, String cotegory) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessages(connection, begin, end, cotegory);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void delMessage(String del) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			messageDao.delUserMessages(connection, del);

			commit(connection);

			return;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<UserComment> getUserComment() {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			List<UserComment> ret = commentDao.getUserComment(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao usercommentDao = new CommentDao();
			usercommentDao.insert(connection, comment);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



}
