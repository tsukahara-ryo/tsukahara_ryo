package tsukahara_ryo.service;

import static tsukahara_ryo.utils.CloseableUtil.*;
import static tsukahara_ryo.utils.DBUtil.*;

import java.sql.Connection;

import tsukahara_ryo.beans.Comment;
import tsukahara_ryo.dao.CommentDao;
import tsukahara_ryo.dao.UserCommentDao;

public class CommentService {

	public void register(Comment comment){

		Connection connection = null;

		try{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

			commit(connection);
		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}catch (Error e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}
	public void delComment(String del) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao commentDao = new UserCommentDao();
			commentDao.delUserComment(connection, del);

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
}



