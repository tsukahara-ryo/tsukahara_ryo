package tsukahara_ryo.service;

import static tsukahara_ryo.utils.CloseableUtil.*;
import static tsukahara_ryo.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import tsukahara_ryo.beans.Position;
import tsukahara_ryo.beans.UserComment;
import tsukahara_ryo.dao.CommentDao;
import tsukahara_ryo.dao.PositionDao;

public class PositionService {



	public List<Position> getPosition()  {

		Connection connection = null;
		try {
			connection = getConnection();

			PositionDao positionDao = new PositionDao();
			List<Position> ret = positionDao.getPosition(connection);

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
}
