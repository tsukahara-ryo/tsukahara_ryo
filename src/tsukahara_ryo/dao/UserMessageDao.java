package tsukahara_ryo.dao;

import static tsukahara_ryo.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import tsukahara_ryo.beans.UserMessage;
import tsukahara_ryo.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection, String begin, String end, String cotegory) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ");
			sql.append("where insert_date BETWEEN ? and ? ");
			if(StringUtils.isEmpty(cotegory) == false){

				String regex = "　";
				String result = cotegory.replaceAll(regex, " ");
				String[] cot = result.split(" ", -1);
				if (cot.length > 1){
				sql.append("and cotegory = ? " );
				sql.append("or cotegory = ? and insert_date BETWEEN ? and ?" );
				}else{
					sql.append("and cotegory = ? " );
				}
			}
			sql.append(" order by insert_date DESC ");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, String.format("%s 00:00:00", begin));
			ps.setString(2, String.format("%s 23:59:59", end));
			if(StringUtils.isEmpty(cotegory) == false){
				String regex = "　";
				String result = cotegory.replaceAll(regex, " ");
				String[] cot = result.split(" ", -1);
				if (cot.length > 1){
				ps.setString(3, cot[0]);
				ps.setString(4, cot[1]);
				ps.setString(5, String.format("%s 00:00:00", begin));
				ps.setString(6, String.format("%s 23:59:59", end));
				}else{
					ps.setString(3, cot[0]);
				}
			}
			ps.executeQuery();

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int messagesId = rs.getInt("id");
				String subject = rs.getString("subject");
				String cotegory= rs.getString("cotegory");
				int userId = rs.getInt("user_id");
				String text = rs.getString("text");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserMessage message = new UserMessage();
				message.setMessagesId(messagesId);
				message.setSubject(subject);
				message.setCotegory(cotegory);
				message.setUserId(userId);
				message.setText(text);
				message.setName(name);
				message.setInsertDate(insertDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void delUserMessages(Connection connection, String del) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete FROM messages where id = ? ");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, del);
			ps.executeUpdate();


			return;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
