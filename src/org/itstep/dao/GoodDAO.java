package org.itstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.itstep.dao.DBConnection;
import org.itstep.model.Goods;

public class GoodDAO {
	public void save(Goods good) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO goods (asin, name, price, url, intalPrice ) VALUES(?,?,?,?,?)";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, good.getAsin());
			statement.setString(2, good.getName());
			statement.setInt(3, good.getPrice());
			statement.setString(4, good.getUrl());
			statement.setInt(5, good.getIntalPrice());
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

	public Goods get(String asin) {

		Goods good = new Goods();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM goods WHERE asin=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, asin);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				good.setAsin(resultSet.getString("asin"));
				good.setName(resultSet.getString("name"));
				good.setPrice(resultSet.getInt("price"));
				good.setIntalPrice(resultSet.getInt("intalPrice"));
				good.setUrl(resultSet.getString("url"));
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
		return good;
	}

	public void update(String asin, Goods newGood) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "UPDATE goods SET asin=?, name=?, price=?, intalPrice=?, url=? WHERE asin=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(6, asin);

			statement.setString(1, newGood.getAsin());
			statement.setString(2, newGood.getName());
			statement.setInt(3, newGood.getPrice());
			statement.setInt(4, newGood.getIntalPrice());
			statement.setString(5, newGood.getUrl());

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

	public void delete(String asin) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM goods WHERE asin=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, asin);
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
