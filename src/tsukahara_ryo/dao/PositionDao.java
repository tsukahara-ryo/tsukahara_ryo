package tsukahara_ryo.dao;

import static tsukahara_ryo.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tsukahara_ryo.beans.Position;

import tsukahara_ryo.exception.SQLRuntimeException;

public class PositionDao {

	public List<Position> getPosition(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM positions";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Position> positionList = toPositionList(rs);
			return positionList;



		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Position> toPositionList(ResultSet rs) throws SQLException {

		List<Position> ret = new ArrayList<Position>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Position position = new Position();
				position.setId(id);
				position.setName(name);

				ret.add(position);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
