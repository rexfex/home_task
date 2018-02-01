package org.itstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.itstep.dao.DBConnection;
import org.itstep.model.GoodAction;

public class ActionDao {
	public void save(GoodAction goodaction) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO good_action (id, asin,login,action,actionTime) VALUES(?,?,?,?)";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, goodaction.getId());
			statement.setString(2, goodaction.getAsin());
			statement.setString(3, goodaction.getLogin());
			statement.setString(4, goodaction.getAction());
			statement.setLong(5, goodaction.getActionTime());
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
		String sql = "SELECT * FROM good_action WHERE login=?";
		
		
		
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				goodAction.setId(resultSet.getInt("id"));
				goodAction.setActionTime(resultSet.getInt("actionTime"));
				goodAction.setAsin(resultSet.getString("asin"));
				goodAction.setLogin(resultSet.getString("login"));
				goodAction.setAction(resultSet.getString("action"));
			}					
		} catch (SQLException e) {					
			e.printStackTrace();
		}				
		finally {					
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
	
	public void update(String login, GoodAction newgoodsAction) {
		Connection connection = null;
		PreparedStatement statement = null;		
		String sql = "UPDATE good_action SET id=?, asin=?, login=?, action=?, action_time=? WHERE login=?";		
		
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(6, login);
			
			statement.setInt(1, newgoodsAction.getId());			
			statement.setString(2, newgoodsAction.getAsin());
			statement.setString(3, newgoodsAction.getLogin());
			statement.setString(4, newgoodsAction.getAction());
			statement.setInt(5, newgoodsAction.getActionTime());
					
			statement.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
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
		String sql = "DELETE FROM good_action WHERE login=?";		
		
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);			
			statement.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
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
