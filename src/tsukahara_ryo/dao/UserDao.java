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

import tsukahara_ryo.beans.Branch;
import tsukahara_ryo.beans.Position;
import tsukahara_ryo.beans.User;
import tsukahara_ryo.exception.NoRowsUpdatedRuntimeException;
import tsukahara_ryo.exception.SQLRuntimeException;

public class UserDao {

	public User getUsers(Connection connection, String login_id,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE (login_id = ? ) AND (password = ? ) AND is_deleted = 0 ";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);


			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public List<User> getAllUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users ORDER BY branch_id, position_id ASC ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			return userList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int position_id = rs.getInt("position_id");
				int is_deleted = rs.getInt("is_deleted");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranch_id(branch_id);
				user.setPosition_id(position_id);
				user.setIs_deleted(is_deleted);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User users) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES (");
			sql.append("?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, users.getLogin_id());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setInt(4, users.getBranch_id());
			ps.setInt(5, users.getPosition_id());

			ps.executeUpdate();


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
		System.out.println(ps.toString());
	}

	public void update(Connection connection, User users) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  login_id = ?");
			if(StringUtils.isEmpty(users.getPassword()) == false){
				sql.append(", password = ?");
			}
			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", position_id = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, users.getLogin_id());
			if(StringUtils.isEmpty(users.getPassword()) == false){
				ps.setString(2, users.getPassword());
				ps.setString(3, users.getName());
				ps.setInt(4, users.getBranch_id());
				ps.setInt(5, users.getPosition_id());
				ps.setInt(6 ,users.getId());
				int count = ps.executeUpdate();
				if (count == 0) {
					throw new NoRowsUpdatedRuntimeException();
				}return;
			}
			ps.setString(2, users.getName());
			ps.setInt(3, users.getBranch_id());
			ps.setInt(4, users.getPosition_id());
			ps.setInt(5 ,users.getId());
			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void isDelet(Connection connection, String del, String id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update users set is_deleted = ? where id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, del);
			ps.setString(2, id);

			ps.executeUpdate();


			return;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public List<Branch> getBranch(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches";

			ps = connection.prepareStatement(sql);

			ps.executeQuery();

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Branch> toBranchList(ResultSet rs)
			throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {
				int branchid = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(branchid);
				branch.setName(name);


				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Position> getPosition(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM positions";

			ps = connection.prepareStatement(sql);
			ps.executeQuery();

			ResultSet rs = ps.executeQuery();
			List<Position> ret = toPositionList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Position> toPositionList(ResultSet rs)
			throws SQLException {

		List<Position> ret = new ArrayList<Position>();
		try {
			while (rs.next()) {
				int positionid = rs.getInt("id");
				String name = rs.getString("name");

				Position position = new Position();
				position.setId(positionid);
				position.setName(name);


				ret.add(position);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public User getUser(Connection connection, String loginid, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE login_id = ? and id <> ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, loginid);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();

			List<User> users = toUserList(rs);

			if (users.isEmpty())
				return null;
			return users.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getSignUpUser(Connection connection, String loginid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE login_id = ? ");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, loginid);
			ResultSet rs = ps.executeQuery();

			List<User> users = toUserList(rs);

			if (users.isEmpty())
				return null;
			return users.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void getId(Connection connection, String id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select id = ? from users ");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			ps.executeQuery();

			return;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
