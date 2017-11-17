package tsukahara_ryo.dao;

import static tsukahara_ryo.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tsukahara_ryo.beans.Comment;
import tsukahara_ryo.beans.UserComment;
import tsukahara_ryo.exception.SQLRuntimeException;

public class CommentDao {


	public List<UserComment> getUserComment(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comment ");

			ps = connection.prepareStatement(sql.toString());


			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserComment(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserComment(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int id =rs.getInt("id");
				String text = rs.getString("text");
				int user_id = rs.getInt("user_id");
				int message_id = rs.getInt("message_id");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserComment comment = new UserComment();
				comment.setId(id);
				comment.setText(text);
				comment.setUser_id(user_id);
				comment.setInsertDate(insertDate);
				comment.setMessage_id(message_id);
				comment.setName(name);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append(" text");
			sql.append(", user_id");
			sql.append(", message_id");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // text
			sql.append(", ?"); // user_id
			sql.append(", ?"); // message_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUser_id());
			ps.setInt(3, comment.getMessage_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}




}
