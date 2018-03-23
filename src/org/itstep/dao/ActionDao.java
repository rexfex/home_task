package org.itstep.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;

import org.itstep.dao.DBConnection;
import org.itstep.model.GoodAction;

public class ActionDao {
	public void save(GoodAction goodaction) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO good_actions (actionTime,login, asin,added_to_wl,added_to_cart) VALUES(?,?,?,?,?)";
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setTime(1, Time.valueOf(LocalTime.now()));
			statement.setString(2, goodaction.getLogin());
			statement.setString(3, goodaction.getAsin());
			statement.setBoolean(4, goodaction.isAdded_to_wl());
			statement.setBoolean(5, goodaction.isAdded_to_cart());
			statement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public GoodAction get(String login) {

		GoodAction goodAction = new GoodAction();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM good_actions WHERE login=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				goodAction.setId(resultSet.getInt("id"));
				goodAction.setActionTime(resultSet.getTime("actiontime"));
				goodAction.setAsin(resultSet.getString("asin"));
				goodAction.setLogin(resultSet.getString("login"));
				goodAction.setAdded_to_wl(resultSet.getBoolean("added_to_wl"));
				goodAction.setAdded_to_cart(resultSet.getBoolean("added_to_cart"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goodAction;
	}

	public void update(String asin, GoodAction newgoodsAction) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "UPDATE good_actions SET  actionTime =?, login=?, asin=?, added_to_wl=?,added_to_cart=? WHERE asin=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(6, asin);

			 statement.setTime(1, newgoodsAction.getActionTime());

			statement.setString(2, newgoodsAction.getLogin());
			statement.setString(3, newgoodsAction.getAsin());
			statement.setBoolean(4, newgoodsAction.isAdded_to_wl());
			statement.setBoolean(5, newgoodsAction.isAdded_to_cart());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delete(String login) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM good_actions WHERE login=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
