package tsukahara_ryo.dao;

import static tsukahara_ryo.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tsukahara_ryo.beans.Message;
import tsukahara_ryo.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append("user_id");
			sql.append(", text");
			sql.append(", cotegory");
			sql.append(", subject");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // user_id
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // subject
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUser_id());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCotegory());
			ps.setString(4, message.getSubject());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
