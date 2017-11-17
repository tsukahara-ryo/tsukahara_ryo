package tsukahara_ryo.service;

import static tsukahara_ryo.utils.CloseableUtil.*;
import static tsukahara_ryo.utils.DBUtil.*;

import java.sql.Connection;

import tsukahara_ryo.beans.User;
import tsukahara_ryo.dao.UserDao;
import tsukahara_ryo.utils.CipherUtil;

public class LoginService {

	public User login(String login_id, String password, String is_deleted) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao
					.getUsers(connection, login_id, encPassword, is_deleted);

			commit(connection);

			return user;
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
