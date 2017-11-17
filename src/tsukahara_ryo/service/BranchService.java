package tsukahara_ryo.service;

import static tsukahara_ryo.utils.CloseableUtil.*;
import static tsukahara_ryo.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import tsukahara_ryo.beans.Branch;
import tsukahara_ryo.beans.UserComment;
import tsukahara_ryo.dao.BranchDao;
import tsukahara_ryo.dao.CommentDao;

public class BranchService {



	public List<Branch> getBranch()  {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> ret = branchDao.getBranch(connection);

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
